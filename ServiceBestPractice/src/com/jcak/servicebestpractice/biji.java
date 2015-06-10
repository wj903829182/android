package com.jcak.servicebestpractice;

public class biji {
/*
 Android中实现定时任务一般有两种方式，一种是使用java api里提供的Timer类，一种是使用android的Alarm机制。
 这两种方式在多数情况下都能实现类似的效果，但是Timer有一个明显的短板，它并不太适用于那些需要长期在后台运行的定时
 任务。我们都知道，为了能让电池更加耐用，每种手机都会有自己的休眠策略，andorid手机就会在长时间不操作的情况下
 自动让cpu进入的到睡眠状态，这就有可能导致Timer中的定时任务无法正常运行。而Alarm机制不存在这种情况，它具有
 唤醒cpu的功能，即可以保证每次需要执行定时任务的时候cpu都能正常工作。需要注意，这里的唤醒cpu和唤醒屏幕完全不是
 同一个概念，不要弄混淆了。
   我们来看看Alarm机制的用法吧，主要需要借助AlarmManager类来实现。这个类和NotificationManager有点类似，
   都是通过调用Context的getSystemService()方法来获取实例的，只是这里需要传入的参数是Context.ALARM_SERVICE.
   因此，获取一个AlarmManager的实例就可以写成：
   AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
   接下来调用AarmManager的set()方法就可以设置一个定时任务了，比如说想要设定一个任务在10秒后执行，就
   可以写成：
   long triggerAtTime=SystemClock.elapsedRealtime()+10*1000;
   manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
   第一个参数是一个整形参数，用于指定AlarmManager的工作类型，有四种值可选，分别是ELAPSED_REALTIME,ELAPSED_REALTIME_WAKEUP,
   RTC 和   RTC_WAKEUP。其中ELAPSED_REALTIME表示让定时任务的触发从系统开机开始算起，但不会唤醒cpu。
   ELAPSED_REALTIME_WAKEUP同样表示让定时任务的触发时间从系统开机开始算起，但会唤醒cpu。
   RTC表示让定时任务的触发时间从1970年1月1日0点开始算起，但不会唤醒cpu。RTC_WAKEUP同样表示让定时
   任务的触发时间从1970年1月1日0点开始算起，但会唤醒cpu。使用SystemClock.elapsedRealtime()方法
   可以获取到系统开机至今所历经的毫秒数，使用System.currentTimeMillis()方法可以获取到1970年1月1日0点
   至今所经历时间的毫秒数。
     第二个参数就是定时任务触发的时间，以毫秒为单位。如果第一个参数使用的是ELAPSED_REALTIME或ELAPSED_REALTIME_WAKEUP则
  这里传入开机至今的时间在加上延迟执行的时间。如果第一个参数使用的是RTC或RTC_WAKEUP，则这里
  传入1970年1月1日0点至今的时间再加上延迟执行的时间。
  第三个参数是一个PendingIntent，对于它应该不会陌生了 吧。这里我们一般会调用getBroadcast()方法来
  获取一个能够执行广播的PendingIntent。这样当定时任务被触发的时候，广播接收器的onReceive()方法就可以得到执行。
  了解了 set()方法的每个参数之后，你应该能想到，设定一个任务在10秒后执行还可以写成：
  long triggerAtTime=System.curentTimeMillis()+10*1000;
  manager.set(AlarmManager.RTC_WAKEUP,triggerAtTime,pendingIntent);
  现在已经掌握了Alarm机制的基本用法，下面我们就来创建一个可以长期在后台执行定时任务的服务。创建一个ServiceBestPractice项目，
  然后新增一个LongRunningService类，代码如下所示：
   
 
 * */
}
