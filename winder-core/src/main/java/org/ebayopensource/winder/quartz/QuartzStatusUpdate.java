/**
 * Copyright (c) 2016 eBay Software Foundation. All rights reserved.
 *
 * Licensed under the MIT license.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 *
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.ebayopensource.winder.quartz;

import org.ebayopensource.winder.StatusEnum;
import org.ebayopensource.winder.StatusUpdate;
import org.ebayopensource.winder.WinderEngine;
import org.quartz.JobDataMap;

import static org.ebayopensource.winder.quartz.QuartzWinderConstants.*;

/**
 * Status Update
 *
 * @author Sheldon Shao xshao@ebay.com on 10/16/16.
 * @version 1.0
 */
public class QuartzStatusUpdate extends QuartzStatusBase<StatusEnum> implements StatusUpdate {

    public QuartzStatusUpdate(WinderEngine engine, JobDataMap jobDataMap, String id, StatusEnum statusEnum, String message) {
        this(engine, jobDataMap, id);

        String key = genKey(KEY_STATUS_UPDATE_EXECUTION_STATUS);
        jobDataMap.put(key, statusEnum.name());

        key = genKey(KEY_STATUS_UPDATE_MESSAGE);
        jobDataMap.put(key, QuartzJobUtil.formatString(message, maxMessages, true));
    }

    public QuartzStatusUpdate(WinderEngine engine, JobDataMap jobDataMap, String id) {
        super(engine, jobDataMap, id);
    }

    @Override
    protected String getKeyDateCreated() {
        return KEY_STATUS_UPDATE_CREATED;
    }

    @Override
    protected String getKeyMessage() {
        return KEY_STATUS_UPDATE_MESSAGE;
    }

    @Override
    protected String getKeyStatus() {
        return KEY_STATUS_UPDATE_EXECUTION_STATUS;
    }

    @Override
    public StatusEnum getStatus() {
        return super.getStatus(StatusEnum.class);
    }
}
