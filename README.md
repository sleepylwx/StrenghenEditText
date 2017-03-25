# StrengthenEditText





加强的EditText，适用于需要对EditText中的内容进行控制的情况，例如：登录时的用户输入的一键清除，用户输入是否可见的控制，添加对于用户输入的辅助等等


控件通过集成一个EditText和若干个ImageView，ImageView负责进行EditText的内容控制，依旧拿登录举例：登录时用户需要点击右方按钮弹出下拉菜单查看以往登录的用户
或者一键将内容变为不可见，等等需求，都需要在布局中添加一个ImageView进行用户交互，本控件正是将需要用户自己实现的功能集成到一起



StrengthenEditText是一个非常轻量的控件，本质是继承的RelativeLayout，内部只是实例化了一个EditText，ImageView的部分通过一个List<ImageView>来引用，因此需要用户在自己代码中显示书写，
并通过参数传给控件，这么做的原因是作者希望打造一个通用的控件，若在内部高度实现某些只在特定场景下才应用到的功能便丧失了这一特性。因此控件只提供的给EditText
和List<Imageview>布局的功能，以及提供对外接口来让用户更改EditText和各个ImageView的各种属性。关于对EditText和List<ImageView>的布局，因为大部分情况下
都是左边提供输入，在右边提供按钮进行用户对输入的更改，因此本控件对EditText和各个ImageView的布局也采用这种方式



# 因此StrengthenEditText的功能只有以下几点：


* 内部实例化了一个EditText，不需要用户关心它的构造，内部已经将其实现
* 内部维持一个List<ImageView>的引用，需要用户显示将实例化的对象传入。
* 控件实现了对EditText和ImageView列表的位置布局，这一点不需要用户书写，EditText位于StrengthenEditText的最左边，List<ImageView>的各个ImageView，按照从List.size()-1的顺序从StrengthenEditText的最右边依次往左排开。
* 提供接口供用户改变StrengthenEditText内部各个View的属性，比如padding，margin，editText的text等。


# PS:下文中的控件API中的Component就是List<ImageView>的意思，例如setComponentVisibility(int index,int visibility);index就是索引号用来获得List中的某个ImageView。


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

# MORE

setDefaultTheme()是将背景设为白色，将EditText的光标设为黑色，这个函数是为了当时方便自己使用而加上的。

代码中可能有些需要修改EditText或者ImageView的某些属性的API没有加上，若在使用中遇到这种情况，欢迎提issue或者fork后提交修改


