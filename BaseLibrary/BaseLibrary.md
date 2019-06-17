##BaseLibrary
经常使用的一些总汇，包括mvp的基类，Rxjava+retrofit网络请求的实现，以及常用的工具类，
## Mvp ##
mvp的封装，拿来直接使用即可。

- BaseActivity
- BaseFragment
- BasePresenter
- BaseView

创建对应的类分别继承对应的基类，实现其方法即可。

## service ##
基于Retrofit+Rxjava+Okhttp的封装。

使用：

        String BASE_URL = "http://www.wanandroid.com";
        ApiManager.getInstance()
                .setService(BASE_URL,WeatherService.class)
                .bannerData()
                .compose(ApiManager.<BannerBean>toMainThread())
                .subscribe(new MObserver<BannerBean>(null) {
                    @Override
                    protected void success(BannerBean bannerBean) {
                        
                    }

                    @Override
                    protected void error(String error) {
                       
                    }
                });

##工具类

- ActivityManage activity管理
- CheckPermission 权限检查
- FitStateUI 状态栏
- NotificationDialogUtils 自定义通知权限弹窗
- SharePreConfigUtils  SharedPreferences存取工具类

##skin 
注：换肤工具类

换肤的本质就是对资源文件的替换，具体流程就是下载apk资源包然后进行替换换肤。

**皮肤包：**

换肤就是替换资源文件，Android应用程序有代码和资源文件组成，所以皮肤就是一个仅包含资源的apk文件。

- 皮肤是个apk文件
- 换肤步骤：下载资源文件-获取资源-替换资源文件
###皮肤包创建
正常创建一个Android工程（red-skin）,不需要java文件，比如替换颜色只需要在color.xml中添加两个相同名称的颜色值即可。

皮肤包 color.xml

    <resources>
    <color name="mainText">#03e0fd</color>
    <color name="mainButton">#7a24e2</color>
    </resources>

主项目 color.xml

    <resources>
    <color name="mainText">#03e0fd</color>
    <color name="mainButton">#008577</color>
    </resources>

通过以上对比可以发现颜色名称相同，颜色值不同。

资源文件添加完成之后，build-build APK 即可打出资源包，按照自己的主题重新命名即可，为了防止下载之后会被系统识别安装包，建议后缀名改为skin（red-skin.skin）。

###皮肤工具类的使用：

在Mapplication中初始化：

    public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
    }

在activity中使用，新建一个BaseActivity

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinFactory = new MySkinFactory();
        getLayoutInflater().setFactory(mSkinFactory);
        super.onCreate(savedInstanceState);  
        SkinManager.getInstance().addSkinUpdateListener(this);
    }
注意：设置Factory的代码要放在 super.onCreate(savedInstanceState) 之前，上面还有一句代码是设置皮肤更新的监听器，实现如下：

    @Override
    public void onSkinUpdate() {
        mySkinFactory.apply();
    }


切换皮肤：

     SkinManager.getInstance().loadSkin(Environment.getExternalStorageDirectory()
                                .getAbsolutePath()+ File.separator+"blue-skin.skin");

皮肤恢复最初：

    SkinManager.getInstance().restoreDefaultTheme();