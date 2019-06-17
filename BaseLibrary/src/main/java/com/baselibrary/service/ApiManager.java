package com.baselibrary.service;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/2
 */
public class ApiManager {
    public static ApiManager getInstance() {
        return new ApiManager();
    }

    public <T> T setService(String baseUrl, Class<T> service) {
        return ReManager.getInstance(baseUrl).create(service);
    }


    /**
     * 处理code
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> filterData() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
                return upstream
                        .filter(new Predicate<BaseBean<T>>() {
                            @Override
                            public boolean test(BaseBean<T> bean) throws Exception {
                                if (bean.getErrorCode() == 0) {
                                    if (null != bean.getData()) {
                                        return true;
                                    } else {
                                        throw new ApiException("暂无数据");
                                    }
                                } else {
                                    throw new ApiException(bean.getErrorMsg());
                                }
                            }
                        })
                        .map(new Function<BaseBean<T>, T>() {
                            @Override
                            public T apply(BaseBean<T> tBaseBean) throws Exception {
                                return tBaseBean.getData();
                            }
                        });
            }
        };
    }

    /**
     * rx切换到主线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> toMainThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
