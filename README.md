# StateUi
=========
##这里是StateUi
你可以用他来减少 在每个Activity或者Fragment里面去手动添加不同状态的布局
###添加依赖

   在你的project 的 build.gradle 中加入
   
    allprojects {
		repositories {
			
			maven { url 'https://jitpack.io' }
		}
	}
  
  然后在你的app 的 build.gradle 中加入 
  
    dependencies {
	        compile 'com.github.1015156849:StateUi:0.0.2'
	}
  
  或者  [查看最新](https://jitpack.io/#1015156849/StateUi "点我，快点我")
  
###使用方法
需要使用到的Activity请继承

    BaseUIActivity
需要使用到的Fragment请继承 

    BaseUIFragment
	
推荐你去继承一个 StateUi 类，这个类默认有一个 

	public static final int ShowDatasView = 0; 
你将你所有自定义的状态布局的layout放在这里，例如：
	
	public class myStateUi extends StateUi {

	    /**默认有一个ShowDatasView 该初始值为0*/
	    public static final int LoadView= R.layout.state_ui_load;
	    public static final int NoNetView= R.layout.state_ui_no_net;
	}
	
在使用的时候，你只需要给需要显示的容器设置一个id，然后调用 

	ShowStateUi(位置容器的id,想显示的StateUi布局);
	
例如：	

	/**显示StateUi（注：该方法中默认先执行了 一次 HideStateUi()）*/
	ShowStateUi(R.id.containerView, myStateUi.LoadView);
	
####注意：位置容器只能为 布局，不可以是Button等控件

###隐藏状态布局
	
	HideStateUi();
	
或者
	
	ShowStateUi(位置容器的id,StateUi中的ShowDatasView);

##如果我想给自定义布局设置一些的控件设置监听怎么办？
  你可以通过 

		setOnClick(StateUi布局,点击监听,想要设置监听的控件id的List集合);

  例如：
	
	          /**设置点击事件 （注：必须使用ShowStateUi()显示 该状态布局 后才能对该布局上的控件设置监听）*/
                setOnClick(myStateUi.NoNetView, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.NoNetView:
                                Toast.makeText(getActivity(), "点击了图片", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.Button:
                                Log.e(TAG, "点击了按钮");
                                ShowStateUi(R.id.containerView, myStateUi.LoadView);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ShowStateUi(R.id.containerView, myStateUi.NoNetView);
                                    }
                                }, 3000);
                                break;
                        }
                    }
                }, new ArrayList<Integer>() {{
                    add(R.id.NoNetView);
                    add(R.id.Button);
                }});
		

##后言<br>
  如果你在使用的时候遇到了什么bug，可以在这里提issue。
  
  
