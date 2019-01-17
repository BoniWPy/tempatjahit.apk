/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zopim.android.sdk.api.ObservableTrigger
 *  com.zopim.android.sdk.data.ConnectionPath
 *  com.zopim.android.sdk.data.DataSource
 *  com.zopim.android.sdk.data.LivechatAccountPath
 *  com.zopim.android.sdk.data.LivechatAgentsPath
 *  com.zopim.android.sdk.data.observers.AccountObserver
 *  com.zopim.android.sdk.data.observers.AgentsObserver
 *  com.zopim.android.sdk.data.observers.ChatLogObserver
 *  com.zopim.android.sdk.data.observers.ConnectionObserver
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedHashMap
 *  java.util.Map
 *  java.util.Observer
 */
package com.zopim.android.sdk.data;

import com.zopim.android.sdk.api.ObservableTrigger;
import com.zopim.android.sdk.data.ConnectionPath;
import com.zopim.android.sdk.data.DataSource;
import com.zopim.android.sdk.data.LivechatAccountPath;
import com.zopim.android.sdk.data.LivechatAgentsPath;
import com.zopim.android.sdk.data.LivechatChatLogPath;
import com.zopim.android.sdk.data.LivechatDepartmentsPath;
import com.zopim.android.sdk.data.LivechatFileSendingPath;
import com.zopim.android.sdk.data.LivechatFormsPath;
import com.zopim.android.sdk.data.LivechatProfilePath;
import com.zopim.android.sdk.data.observers.AccountObserver;
import com.zopim.android.sdk.data.observers.AgentsObserver;
import com.zopim.android.sdk.data.observers.ChatLogObserver;
import com.zopim.android.sdk.data.observers.ConnectionObserver;
import com.zopim.android.sdk.data.observers.DepartmentsObserver;
import com.zopim.android.sdk.data.observers.FileSendingObserver;
import com.zopim.android.sdk.data.observers.FormsObserver;
import com.zopim.android.sdk.data.observers.ProfileObserver;
import com.zopim.android.sdk.model.Account;
import com.zopim.android.sdk.model.Agent;
import com.zopim.android.sdk.model.ChatLog;
import com.zopim.android.sdk.model.Connection;
import com.zopim.android.sdk.model.Department;
import com.zopim.android.sdk.model.FileSending;
import com.zopim.android.sdk.model.Forms;
import com.zopim.android.sdk.model.Profile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observer;

public class PathDataSource
implements DataSource {
    private LivechatAccountPath accountPath = LivechatAccountPath.getInstance();
    private LivechatAgentsPath agentsPath = LivechatAgentsPath.getInstance();
    private LivechatChatLogPath chatLogPath = LivechatChatLogPath.getInstance();
    private ConnectionPath connectionPath = ConnectionPath.getInstance();
    private LivechatDepartmentsPath departmentsPath = LivechatDepartmentsPath.getInstance();
    private LivechatFileSendingPath fileSendingPath = LivechatFileSendingPath.getInstance();
    private LivechatFormsPath formsPath = LivechatFormsPath.getInstance();
    private LivechatProfilePath profilePath = LivechatProfilePath.getInstance();

    public ObservableTrigger addAccountObserver(AccountObserver accountObserver) {
        this.accountPath.addObserver((Observer)accountObserver);
        return this.accountPath;
    }

    public ObservableTrigger addAgentsObserver(AgentsObserver agentsObserver) {
        this.agentsPath.addObserver((Observer)agentsObserver);
        return this.agentsPath;
    }

    public ObservableTrigger addChatLogObserver(ChatLogObserver chatLogObserver) {
        this.chatLogPath.addObserver((Observer)chatLogObserver);
        return this.chatLogPath;
    }

    public ObservableTrigger addConnectionObserver(ConnectionObserver connectionObserver) {
        this.connectionPath.addObserver((Observer)connectionObserver);
        return this.connectionPath;
    }

    public ObservableTrigger addDepartmentsObserver(DepartmentsObserver departmentsObserver) {
        this.departmentsPath.addObserver((Observer)departmentsObserver);
        return this.departmentsPath;
    }

    public ObservableTrigger addFileSendingObserver(FileSendingObserver fileSendingObserver) {
        this.fileSendingPath.addObserver((Observer)fileSendingObserver);
        return this.fileSendingPath;
    }

    public ObservableTrigger addFormsObserver(FormsObserver formsObserver) {
        this.formsPath.addObserver((Observer)formsObserver);
        return this.formsPath;
    }

    public ObservableTrigger addProfileObserver(ProfileObserver profileObserver) {
        this.profilePath.addObserver((Observer)profileObserver);
        return this.profilePath;
    }

    public void clear() {
        this.connectionPath.clear();
        this.profilePath.clear();
        this.accountPath.clear();
        this.agentsPath.clear();
        this.departmentsPath.clear();
        this.chatLogPath.clear();
        this.formsPath.clear();
        this.fileSendingPath.clear();
    }

    public void deleteAccountObserver(AccountObserver accountObserver) {
        this.accountPath.deleteObserver((Observer)accountObserver);
    }

    public void deleteAgentsObserver(AgentsObserver agentsObserver) {
        this.agentsPath.deleteObserver((Observer)agentsObserver);
    }

    public void deleteChatLogObserver(ChatLogObserver chatLogObserver) {
        this.chatLogPath.deleteObserver((Observer)chatLogObserver);
    }

    public void deleteConnectionObserver(ConnectionObserver connectionObserver) {
        this.connectionPath.deleteObserver((Observer)connectionObserver);
    }

    public void deleteDepartmentsObserver(DepartmentsObserver departmentsObserver) {
        this.departmentsPath.deleteObserver((Observer)departmentsObserver);
    }

    public void deleteFileSendingObserver(FileSendingObserver fileSendingObserver) {
        this.fileSendingPath.deleteObserver((Observer)fileSendingObserver);
    }

    public void deleteFormsObserver(FormsObserver formsObserver) {
        this.formsPath.deleteObserver((Observer)formsObserver);
    }

    public void deleteObservers() {
        this.connectionPath.deleteObservers();
        this.profilePath.deleteObservers();
        this.accountPath.deleteObservers();
        this.agentsPath.deleteObservers();
        this.departmentsPath.deleteObservers();
        this.chatLogPath.deleteObservers();
        this.formsPath.deleteObservers();
        this.fileSendingPath.deleteObservers();
    }

    public void deleteProfileObserver(ProfileObserver profileObserver) {
        this.profilePath.deleteObserver((Observer)profileObserver);
    }

    public Account getAccount() {
        return this.accountPath.getData();
    }

    public LinkedHashMap<String, Agent> getAgents() {
        return this.agentsPath.getData();
    }

    public LinkedHashMap<String, ChatLog> getChatLog() {
        return this.chatLogPath.getData();
    }

    public Connection getConnection() {
        return this.connectionPath.getData();
    }

    public Map<String, Department> getDepartments() {
        return this.departmentsPath.getData();
    }

    public FileSending getFileSending() {
        return this.fileSendingPath.getData();
    }

    public Forms getForms() {
        return this.formsPath.getData();
    }

    public Profile getProfile() {
        return this.profilePath.getData();
    }
}

