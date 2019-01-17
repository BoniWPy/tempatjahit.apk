/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.util.ArrayDeque
 *  java.util.Queue
 *  java.util.concurrent.Executor
 */
package com.zopim.android.sdk.data;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

class SerialExecutor
implements Executor {
    Runnable active;
    final Executor executor;
    final Queue<Runnable> tasks = new ArrayDeque();

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public void execute(final Runnable runnable) {
        SerialExecutor serialExecutor = this;
        synchronized (serialExecutor) {
            this.tasks.add((Object)new Runnable(){

                public void run() {
                    try {
                        runnable.run();
                        return;
                    }
                    finally {
                        SerialExecutor.this.scheduleNext();
                    }
                }
            });
            if (this.active == null) {
                this.scheduleNext();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void scheduleNext() {
        SerialExecutor serialExecutor = this;
        synchronized (serialExecutor) {
            Runnable runnable;
            this.active = runnable = (Runnable)this.tasks.poll();
            if (runnable != null) {
                this.executor.execute(this.active);
            }
            return;
        }
    }

}

