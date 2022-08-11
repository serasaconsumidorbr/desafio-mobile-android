import 'dart:async';
import 'package:flutter/material.dart';
import 'package:marvel_app/Home/login.page.dart';

class SplashPage extends StatefulWidget {
  const SplashPage({Key key}) : super(key: key);

  @override
  _SplashPageState createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  _startSplash() {
    const durationSplash = Duration(seconds: 3);
    return Timer(durationSplash, _homeNavigation);
  }

  void _homeNavigation() {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => LoginPage(),
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    _startSplash();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Color(0xFFbe0f04),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: const [
            Image(
              image: AssetImage("assets/images/logo2.gif"),
            ),
          ],
        ),
      ),
    );
  }
}
