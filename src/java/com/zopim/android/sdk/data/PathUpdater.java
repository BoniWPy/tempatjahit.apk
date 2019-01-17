/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zendesk.logger.Logger
 *  com.zopim.android.sdk.data.ConnectionPath
 *  com.zopim.android.sdk.data.LivechatAccountPath
 *  com.zopim.android.sdk.data.LivechatAgentsPath
 *  java.lang.IndexOutOfBoundsException
 *  java.lang.NoSuchFieldError
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.data;

import com.zendesk.logger.Logger;
import com.zopim.android.sdk.data.ConnectionPath;
import com.zopim.android.sdk.data.LivechatAccountPath;
import com.zopim.android.sdk.data.LivechatAgentsPath;
import com.zopim.android.sdk.data.LivechatChatLogPath;
import com.zopim.android.sdk.data.LivechatDepartmentsPath;
import com.zopim.android.sdk.data.LivechatFileSendingPath;
import com.zopim.android.sdk.data.LivechatFormsPath;
import com.zopim.android.sdk.data.LivechatProfilePath;
import com.zopim.android.sdk.data.PathName;

class PathUpdater {
    private static final boolean DEBUG = false;
    private static final String DELIMITER = ";";
    private static final String LOG_TAG = "PathUpdater";

    PathUpdater() {
    }

    private String getBody(String string) {
        if (string == null) {
            return "";
        }
        try {
            String string2 = string.substring(string.indexOf(DELIMITER) + DELIMITER.length());
            return string2;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            Logger.w((String)LOG_TAG, (String)("Failed to parse the json message in order to retrieve message body. " + indexOutOfBoundsException.getMessage()), (Object[])new Object[0]);
            return "";
        }
    }

    private PathName getPath(String string) {
        if (string == null) {
            return PathName.UNKNOWN;
        }
        try {
            PathName pathName = PathName.get(string.substring(0, -1 + (string.indexOf(DELIMITER) + DELIMITER.length())));
            return pathName;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            Logger.w((String)LOG_TAG, (String)("Failed to parse the json message in order to retrieve path name. " + indexOutOfBoundsException.getMessage()), (Object[])new Object[0]);
            return PathName.UNKNOWN;
        }
    }

    PathName updatePath(String string) {
        String string2 = this.getBody(string);
        PathName pathName = this.getPath(string);
        switch (1.$SwitchMap$com$zopim$android$sdk$data$PathName[pathName.ordinal()]) {
            default: {
                return pathName;
            }
            case 1: {
                LivechatChatLogPath.getInstance().update(string2);
                return pathName;
            }
            case 2: {
                LivechatProfilePath.getInstance().update(string2);
                return pathName;
            }
            case 3: {
                LivechatAgentsPath.getInstance().update(string2);
                return pathName;
            }
            case 4: {
                LivechatDepartmentsPath.getInstance().update(string2);
                return pathName;
            }
            case 5: {
                LivechatAccountPath.getInstance().update(string2);
                return pathName;
            }
            case 6: {
                LivechatFormsPath.getInstance().update(string2);
                return pathName;
            }
            case 7: {
                LivechatFileSendingPath.getInstance().update(string2);
                return pathName;
            }
            case 8: 
        }
        ConnectionPath.getInstance().update(string2);
        return pathName;
    }

}

