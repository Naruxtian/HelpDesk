<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="Calculadora de volumenes"
        android:textAppearance="@style/TextAppearance.AppCompat.Display1"
        android:textColor="@color/design_default_color_primary_dark"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="de peceras"
        android:textAppearance="@style/TextAppearance.AppCompat.Display1"
        android:textColor="@color/design_default_color_primary_dark"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <RadioGroup
        android:id="@+id/radioGroup"
        android:layout_width="376dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        tools:layout_editor_absoluteY="311dp">

        <RadioButton
            android:id="@+id/radioButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Cuadrada"
            android:textAppearance="@style/TextAppearance.AppCompat.Medium" />

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Cilíndrica"
            android:textAppearance="@style/TextAppearance.AppCompat.Medium" />

        <RadioButton
            android:id="@+id/radioButton5"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Esférica"
            android:textAppearance="@style/TextAppearance.AppCompat.Medium" />

        <RadioButton
            android:id="@+id/radioButton6"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            and