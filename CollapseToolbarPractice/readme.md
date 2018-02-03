# Collapse Toolbar Layout을 사용해보자.

## 1. Collapse Toolbar Layout은?
 - 구글에서 안드로이드 디자인을 위해 지원하는 Design Support Library에 포함된 기능.
 - 스크롤에 따라 툴바의 크기가 변형되며, 이미지 등을 넣을 수 있다.
 - Coordinator Layout과 함께 사용한다.

## 2. Collapse Toolbar Layout 사용하기
 - app gradle에 design library가 설치되어 있어야 사용할 수 있다.(사용하려고 하면, 안드로이드 스튜디오에서 자동으로 설치해준다.)
 - 자바 코드가 아닌 xml을 통해 설정할 수 있다.
 - 기본 형태의 코드 사용.
 <pre><code>
 <?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_height="192dp"
        android:layout_width="match_parent">

        <android.support.design.widget.CollapsingToolbarLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:toolbarId="@+id/toolbar"
            app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed"
            app:layout_scrollInterpolator="@android:anim/decelerate_interpolator"
            app:contentScrim="?attr/colorPrimary">

            <ImageView
                android:id="@+id/app_bar_image"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:layout_collapseMode="parallax"
                android:src="@android:drawable/sym_def_app_icon"
                android:scaleType="centerCrop" />

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_height="?attr/actionBarSize"
                android:layout_width="match_parent"></android.support.v7.widget.Toolbar>
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="android.support.design.widget.AppBarLayout$ScrollingViewBehavior">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical" />
    </android.support.v4.widget.NestedScrollView>
</android.support.design.widget.CoordinatorLayout>
 </code></pre>
 - 기본적으로 사용하는 방법 이외에도 Toolbar를 없애거나, image를 LinearLayout으로 바꾸는 등의 튜닝도 가능하다. 