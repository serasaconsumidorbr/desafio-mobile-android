import 'package:flutter/material.dart';
import 'package:marvel_app/Character/character_slider.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: <Widget>[
          SingleChildScrollView(
            child: Container(
              height: MediaQuery.of(context).size.height,
              child: Stack(
                children: <Widget>[
                  Positioned.fill(
                    child: Image.asset(
                      'assets/images/teste7.png',
                      fit: BoxFit.cover,
                    ),
                  ),
                  Positioned(
                    child: Container(
                      width: MediaQuery.of(context).size.width,
                      padding: EdgeInsets.symmetric(
                          horizontal: 20.w, vertical: 50.h),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          Image.asset(
                            'assets/images/logo.png',
                            width: 100.w,
                          ),
                        ],
                      ),
                    ),
                  ),
                  Center(
                    child: Column(
                      children: <Widget>[
                        SizedBox(
                          height: 130.h,
                        ),
                        Card(
                          elevation: 2,
                          color: Color.fromARGB(0, 255, 255, 255),
                          child: Padding(
                            padding: EdgeInsets.symmetric(horizontal: 15.w),
                            child: Column(
                              children: [
                                Text(
                                  'HÉROIS DA MARVEL',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 28.sp,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                                SizedBox(
                                  height: 10.h,
                                ),
                                Padding(
                                  padding: const EdgeInsets.only(bottom: 30),
                                  child: Text(
                                    'Tomem cuidado. Cuidem uns dos outros. Essa é a luta de nossas vidas. E nós vamos vencer.',
                                    style: TextStyle(
                                      color: Colors.white,
                                    ),
                                    textAlign: TextAlign.center,
                                  ),
                                ),
                                Padding(
                                  padding: const EdgeInsets.only(bottom: 30),
                                  child: Text(
                                    '“Ele pode ser o seu pai, garoto nmas não é o seu papai (não foi quem te criou).”',
                                    style: TextStyle(
                                      color: Colors.white,
                                    ),
                                    textAlign: TextAlign.center,
                                  ),
                                ),
                                Padding(
                                  padding: const EdgeInsets.only(bottom: 60),
                                  child: Text(
                                    '“Só porque algo funciona, não significa que não pode ser melhorada.”',
                                    style: TextStyle(
                                      color: Colors.white,
                                    ),
                                    textAlign: TextAlign.center,
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ),
                        Card(
                          elevation: 2,
                          color: Color.fromARGB(0, 0, 0, 0),
                          child: Column(
                            children: [
                              Container(
                                child: CharacterSlider(),
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
