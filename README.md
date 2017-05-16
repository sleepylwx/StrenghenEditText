# StrengthenEditText





StrengthenEditText是一个非常轻量的控件，适用于需要对EditText中的内容进行控制的情况，例如：登录时的用户输入的一键清除，用户输入是否可见的控制，添加对于用户输入的辅助等等





# StrengthenEditText的功能只有以下几点：


* 内部实例化了一个EditText，不需要用户关心它的构造，内部已经将其实现
* 内部维持一个List<ImageView>的引用，需要用户显示将实例化的对象传入。
* 控件实现了对EditText和ImageView列表的位置布局，这一点不需要用户书写，EditText位于StrengthenEditText的最左边，List<ImageView>的各个ImageView，按照从List.size()-1的顺序从StrengthenEditText的最右边依次往左排开。
* 提供接口供用户改变StrengthenEditText内部各个View的属性，比如padding，margin，editText的text等。


# 具体使用：


控件的使用分为创建和配置两步，创建可以在布局文件中创建，也可以在代码中显式创建。配置需要在代码中调用setComponent(List<ImageView> list),给控件
显式提供Component;

## 创建

* 在布局文件中显示书写

<com.lwx.imagelabel.ui.widget.StrengthenEditText
        android:id="@+id/edittext_user"
        android:layout_width="match_parent"
        android:layout_height="50dp" />
        
        
 
 * 在activity中显式创建
 
 StrengthenEditText stEditText = new StrenthenEditText(this);
 
 
## 配置

*在引用相应布局文件的activity中获得控件引用

StrengthenEditText stEditText= (StrengthenEditText)findViewById(R.id.edittext_user);

创建List<ImageView> list = new ArrayList<>();
list.add(...)
...

*列表创建完成后，调用stEditText.setComponent(list);

*调用其他配置控件的API，来配置EditText或者各个ImageView的参数
例如stEditText.setComponentPadding(int )来设置各个ImageView之间的左右Padding，其他API，在使用过程中通过函数名即可明白含义，这里不再描述




