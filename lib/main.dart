import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:marvel_app/Home/splash.page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
        designSize: const Size(360, 900),
        minTextAdapt: true,
        splitScreenMode: true,
        builder: (context, child) {
          return MaterialApp(
            theme: ThemeData(
              textTheme: Typography.englishLike2018.apply(fontSizeFactor: 1.sp),
            ),
            debugShowCheckedModeBanner: false,
            home: SplashPage(),
          );
        });
  }
}
