Źžŗ¾   2 © 6com/appscrip/amit/multibhashi/ViewModel/VideoViewModel   android/arch/lifecycle/ViewModel  	videoList (Landroid/arch/lifecycle/MutableLiveData; gLandroid/arch/lifecycle/MutableLiveData<Ljava/util/List<Lcom/appscrip/amit/multibhashi/Model/Video;>;>; getVideoList #()Landroid/arch/lifecycle/LiveData; #Lorg/jetbrains/annotations/NotNull; 	loadUsers ()V  
    	   android/arch/lifecycle/LiveData  this 8Lcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel; &okhttp3/logging/HttpLoggingInterceptor  <init>  
   ,okhttp3/logging/HttpLoggingInterceptor$Level  BODY .Lokhttp3/logging/HttpLoggingInterceptor$Level;  	   setLevel X(Lokhttp3/logging/HttpLoggingInterceptor$Level;)Lokhttp3/logging/HttpLoggingInterceptor;   !
  " okhttp3/OkHttpClient$Builder $
 %  okhttp3/Interceptor ' addInterceptor 5(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder; ) *
 % + build ()Lokhttp3/OkHttpClient; - .
 % / com/google/gson/GsonBuilder 1
 2  
setLenient ()Lcom/google/gson/GsonBuilder; 4 5
 2 6 create ()Lcom/google/gson/Gson; 8 9
 2 : retrofit2/Retrofit$Builder <
 =  BaseUrl Ljava/lang/String; ? @	  A baseUrl 0(Ljava/lang/String;)Lretrofit2/Retrofit$Builder; C D
 = E -retrofit2/converter/gson/GsonConverterFactory G G(Lcom/google/gson/Gson;)Lretrofit2/converter/gson/GsonConverterFactory; 8 I
 H J retrofit2/Converter$Factory L addConverterFactory ;(Lretrofit2/Converter$Factory;)Lretrofit2/Retrofit$Builder; N O
 = P client 4(Lokhttp3/OkHttpClient;)Lretrofit2/Retrofit$Builder; R S
 = T ()Lretrofit2/Retrofit; - V
 = W 4com/appscrip/amit/multibhashi/Interface/VideoService Y retrofit2/Retrofit [ %(Ljava/lang/Class;)Ljava/lang/Object; 8 ]
 \ ^ ()Lretrofit2/Call;  ` Z a Bcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel$loadUsers$1 c ;(Lcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel;)V  e
 d f retrofit2/Callback h retrofit2/Call j enqueue (Lretrofit2/Callback;)V l m k n call Lretrofit2/Call; service 6Lcom/appscrip/amit/multibhashi/Interface/VideoService; retrofit Lretrofit2/Retrofit; gson Lcom/google/gson/Gson; Lokhttp3/OkHttpClient; interceptor (Lokhttp3/logging/HttpLoggingInterceptor;
   &android/arch/lifecycle/MutableLiveData |
 }  Ahttps://my-json-server.typicode.com/Multibhashi/sample-api/video/  <clinit> 	Companion BLcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel$Companion; access$getVideoList$p b(Lcom/appscrip/amit/multibhashi/ViewModel/VideoViewModel;)Landroid/arch/lifecycle/MutableLiveData; $this Lkotlin/Metadata; mv       bv        k d1 Ą(




 

Ą

Ą

Ą 20:BĀ¢J
000J	0
HR
000XĀĀ¢
ĄĀØ d2 "Landroid/arch/lifecycle/ViewModel;   +Lcom/appscrip/amit/multibhashi/Model/Video; !Landroid/arch/lifecycle/LiveData; 	app_debug @com/appscrip/amit/multibhashi/ViewModel/VideoViewModel$Companion  1(Lkotlin/jvm/internal/DefaultConstructorMarker;)V  
    	   VideoViewModel.kt 	Signature ConstantValue Code LocalVariableTable LineNumberTable b()Landroid/arch/lifecycle/LiveData<Ljava/util/List<Lcom/appscrip/amit/multibhashi/Model/Video;>;>; RuntimeInvisibleAnnotations 
SourceFile InnerClasses RuntimeVisibleAnnotations 1                ? @                	  ”   :     *· *“ Ą °    ¢            £   
           ¤ „     
       ”  N     x» Y· L+² ¶ #W» %Y· &+Ą (¶ ,¶ 0M» 2Y· 3¶ 7¶ ;N» =Y· >² B¶ F-ø KĄ M¶ Q,¶ U¶ X:Z¶ _Ą Z:¹ b :» dY*· gĄ i¹ o ±    ¢   H  e  p q  \  r s  P ( t u  0 H v w  " V R x   p y z    x     £   v       !  " " # " % " # " $ " # ) $ , % / # 0 & 0 * 0 & 0 ) 0 & 0 ( 0 & 0 ' 0 & 7 ' = ( G ) K * N & P - \ . e 0 w =     ”   B     *· {*» }Y· ~µ ±    ¢            £                ”   )      » Y· ³ ³ B±    £      @    ”   /     *“ °    ¢            £        „     
    ¦     §     d          Ø   [    [ I I I  [ I I I  I  [ s  [ s s s s s s s s s s s s s                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       