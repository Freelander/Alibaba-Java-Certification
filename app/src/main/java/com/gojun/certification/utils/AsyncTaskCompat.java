package com.gojun.certification.utils;

import android.os.AsyncTask;

/**
 * Description:
 *
 * @author Jun 11/28/20
 */
public final class AsyncTaskCompat {
    @Deprecated
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> executeParallel(
            AsyncTask<Params, Progress, Result> task,
            Params... params) {
        if (task == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        return task;
    }
    private AsyncTaskCompat() {}
}
