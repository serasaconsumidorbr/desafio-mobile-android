import 'package:flutter/material.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'package:marvel_app/Character/character_page.dart';
import '../constants.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class CharacterSlider extends StatefulWidget {
  @override
  _CharacterSliderState createState() => _CharacterSliderState();
}

class _CharacterSliderState extends State<CharacterSlider> {
  @override
  Widget build(BuildContext context) {
    return CarouselSlider.builder(
      viewportFraction: 0.7,
      autoPlay: true,
      height: 340.h,
      enlargeCenterPage: false,
      itemCount: characterList.length,
      itemBuilder: (context, index) => CharacterCard(index),
    );
  }
}

class CharacterCard extends StatelessWidget {
  final index;

  CharacterCard(this.index);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => CharacterPage(index),
          ),
        );
      },
      child: ClipRRect(
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(10),
          topRight: Radius.circular(10),
          bottomRight: Radius.circular(10),
          bottomLeft: Radius.circular(10),
        ),
        child: Container(
          decoration: BoxDecoration(),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Flexible(
                flex: 5,
                child: Container(
                  child: Hero(
                    tag: characterList[index]['imgUrl'],
                    child: Image.asset(
                      characterList[index]['imgUrl'],
                      fit: BoxFit.fill,
                    ),
                  ),
                ),
              ),
              Container(
                height: 1.h,
                width: MediaQuery.of(context).size.width / 2 - 40.w,
                color: Colors.white,
              ),
              Flexible(
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Text(
                        characterList[index]['hero_name'].toUpperCase(),
                        style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                          fontSize: 16.sp,
                        ),
                      ),
                      Text(
                        characterList[index]['real_name'].toUpperCase(),
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 12.sp,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
