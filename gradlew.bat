����   2 � Bcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel$loadUsers$1  fLjava/lang/Object;Lretrofit2/Callback<Ljava/util/List<+Lcom/appscrip/amit/multibhashi/Model/Video;>;>; java/lang/Object  retrofit2/Callback  6com/appscrip/amit/multibhashi/ViewModel/VideoViewModel  	loadUsers ()V 
  
onResponse '(Lretrofit2/Call;Lretrofit2/Response;)V #Lorg/jetbrains/annotations/NotNull; call  kotlin/jvm/internal/Intrinsics  checkParameterIsNotNull '(Ljava/lang/Object;Ljava/lang/String;)V  
   response  retrofit2/Response  body ()Ljava/lang/Object;  
   throwNpe   
  ! response.body()!! # checkExpressionValueIsNotNull % 
  & java/util/Collection ( isEmpty ()Z * + ) , asdf . java/lang/StringBuilder 0 <init> 2 
 1 3 response size is  5 append -(Ljava/lang/String;)Ljava/lang/StringBuilder; 7 8
 1 9 java/util/List ; -(Ljava/lang/Object;)Ljava/lang/StringBuilder; 7 =
 1 > toString ()Ljava/lang/String; @ A
 1 B android/util/Log D d '(Ljava/lang/String;Ljava/lang/String;)I F G
 E H this$0 8Lcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel; J K	  L access$getVideoList$p b(Lcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel;)Landroid/arch/lifecycle/MutableLiveData; N O
 	 P &android/arch/lifecycle/MutableLiveData R 	postValue (Ljava/lang/Object;)V T U
 S V this DLcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel$loadUsers$1; Lretrofit2/Call; Lretrofit2/Response; 	onFailure ((Lretrofit2/Call;Ljava/lang/Throwable;)V t ^ Ljava/lang/Throwable; ;(Lcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel;)V
  3 $outer Lkotlin/Metadata; mv       bv        k d1 ���-
��

 

��

��

��



��*��
��2
000J$02
00020	HJ0
02
0002
000H¨ d2 Lretrofit2/Callback;   +Lcom/appscrip/amit/multibhashi/Model/Video; 	app_debug VideoViewModel.kt Code LocalVariableTable LineNumberTable StackMapTable 	Signature �(Lretrofit2/Call<Ljava/util/List<Lcom/appscrip/amit/multibhashi/Model/Video;>;>;Lretrofit2/Response<Ljava/util/List<Lcom/appscrip/amit/multibhashi/Model/Video;>;>;