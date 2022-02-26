<p align="center">
  <a href="https://github.com/adrianwitaszak/neumorph-ui">
    <img src="art/banner.png">
  </a>
</p>
<h1 align="center">Neumorph-UI</h1>
<div align="center">

[![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/adrianwitaszak/neumorph-ui)
[![](https://jitci.com/gh/adrianwitaszak/neumorph-ui/svg)](https://jitci.com/gh/adrianwitaszak/neumorph-ui)
![visitors](https://visitor-badge.laobi.icu/badge?page_id=adrianwitaszak)
![](https://img.shields.io/github/stars/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/forks/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/watchers/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/commit-activity/m/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/last-commit/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/repo-size/adrianwitaszak/neumorph-ui)
  
![](https://img.shields.io/tokei/lines/github/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/languages/count/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/languages/top/adrianwitaszak/neumorph-ui)
[![](https://jitpack.io/v/adrianwitaszak/neumorph-ui.svg)](https://jitpack.io/#adrianwitaszak/neumorph-ui)
![](https://img.shields.io/github/v/release/adrianwitaszak/neumorph-ui)
![](https://img.shields.io/badge/Jetpack%20-Compose-yellowgreen)
</div>

[ DOCS](https://adrianwitaszak.github.io/neumorph-ui/index.html)

 ✨ Features

---

- 🌈 Enterprise-class UI designed for Android.
- 📦 A set of high-quality Jetpack Compose components out of the box.
- 🛡 Written in Kotlin.
- ⚙️ Whole package of design resources and development tools.
- 🎨 Powerful theme customization in every detail.

 📦 Install

---

 <img width="30" align="center" src="https://slackmojis.com/emojis/28070-gradle-elephant/download"> Add it in your root build.gradle at the end of repositories:

```terminal
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

 <img width="30" align="center" src="https://slackmojis.com/emojis/32342-add/download"> Add the dependency ![](https://img.shields.io/github/v/release/adrianwitaszak/neumorph-ui)

```bash
dependencies {
    // Gradle
    implementation 'com.github.adrianwitaszak:neumorph-ui:tag'
    
    // Kotlin DSL
    implementation("com.github.adrianwitaszak:neumorph-ui:tag")
    
    // Replace 'tag' with desired version
}
```

 <img width="30" align="center" src="https://slackmojis.com/emojis/4696-android_studio/download"> Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/adrianwitaszak/neumorph-ui.git` into URL field an press `Clone` button

 <img width="30" align="center" src="https://slackmojis.com/emojis/1263-terminal/download"> Command-line + Android Studio

1. Run `git clone https://github.com:adrianwitaszak/neumorph-ui.git` command to clone project
2. Open `Android Studio` and select `File | Open...` from the menu. Select cloned directory and press `Open` button

 🔨 Usage

---

### Two main styles:

[Pressed style](neumorph/src/main/kotlin/com/adwi/neumorph/android/PressedStatic.kt) 
- Pressed Card style
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)

```bash
MorphPressed(
    onClick: () -> Unit = {},
    elevation = 10.dp,
    cornerRadius = 30.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    backgroundColor = MaterialTheme.colors.background,
    lightShadowColor = AppColors.lightShadow(),
    darkShadowColor = AppColors.darkShadow(),
    border = null,
    scale = 1f,
    lightSource = LightSource.LEFT_TOP,
    invertedBackgroundColors = false,
    hasIndication = false,
    modifier = Modifier.height(100.dp),
    content = { Text(text = "MorphPressed") }
) 
```
<img width="600" src="art/pressedlight.png"><img width="600" src="art/presseddark.png">


[Punched style](neumorph/src/main/kotlin/com/adwi/neumorph/android/PunchedStatic.kt)
- Elevated Card style
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)
```bash
MorphPunched(
    onClick: () -> Unit = {},
    elevation = 10.dp,
    cornerRadius = 30.dp,
    shape: Shape = RoundedCornerShape(cornerRadius),
    backgroundColor = MaterialTheme.colors.background,
    lightShadowColor = AppColors.lightShadow(),
    darkShadowColor = AppColors.darkShadow(),
    border = null,
    scale = 1f,
    lightSource = LightSource.LEFT_TOP,
    invertedBackgroundColors = false,
    hasIndication = false,
    modifier = Modifier.height(100.dp),
    content = { Text(text = "MorphPressed") }
)
```
<img width="600" src="art/punchedlight.png"><img width="600" src="art/puncheddark.png">


## Components

---

[Button rounded](neumorph/src/main/kotlin/com/adwi/neumorph/android/ButtonRounded.kt)
- Elevated button
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)
```bash
MorphButtonRounded (
    elevation = 30.dp,
    cornerRadius = 25.dp,
    lightSource = LightSource.LEFT_TOP,
    modifier = Modifier.align(Alignment.Center)
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.surface)
    ) {
        SampleText("ButtonRounded")
    }
}
```
<img width="600" src="art/btnrl.gif"><img width="600" src="art/btnrd.gif">

[Button Oval](neumorph/src/main/kotlin/com/adwi/neumorph/android/ButtonOval.kt)
- Circle button
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)
```bash
MorphButtonOval(
    elevation = 10.dp,
    backgroundColor = MaterialTheme.colors.background,
    contentColor = MaterialTheme.colors.onBackground,
    modifier = Modifier.size(120.dp),
    content = {
        MorphIcon(
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.size(40.dp)
        )
    }
)
```
<img width="600" src="art/btnovall.gif"><img width="600" src="art/btnovald.gif">

[Radio button](neumorph/src/main/kotlin/com/adwi/neumorph/android/RadioButton.kt)
- Animated radio button
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)
```bash
var value by remember { mutableStateOf(false) }

MorphRadioButton(
    elevation = 10.dp,
    radioColor = MaterialTheme.colors.secondary,
    value = value,
    onValueChange = { value = !value },
    modifier = Modifier.size(40.dp)
)
```
<img width="600" src="art/radiol.gif"><img width="600" src="art/radiod.gif">

[Switch](neumorph/src/main/kotlin/com/adwi/neumorph/android/Switch.kt)
- Animated switch
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)
```bash
var value by remember { mutableStateOf(false) }

MorphSwitch(
    elevation = 10.dp,
    cornerRadius = 10.dp,
    switchColor = MaterialTheme.colors.secondary,
    value = value,
    onValueChange = { value = !value },
    modifier = Modifier
        .height(40.dp)
        .width(80.dp)
)
```
<img width="600" src="art/switchl.gif"><img width="600" src="art/switchd.gif">

[Slider](neumorph/src/main/kotlin/com/adwi/neumorph/android/Slider.kt)
- Animated switch
- [Example usage](app/src/main/kotlin/com/adwi/neumorph/sample/ui/screens/HomeScreen.kt)
```bash
 MorphSlider(
    value = .6f,
    onValueChange = {},
    elevation = 5.dp,
    cornerRadius = 10.dp,
    backgroundColor = MaterialTheme.colors.primary,
    modifier = Modifier,
)
```
<img width="600" src="art/sliderl.gif"><img width="600" src="art/sliderd.gif">

## Coming soon

Here is a list of components I'm planning to build in Neumorph UI style.
- TextView 
- Bottom Nav 
- App bar 
- Popup 
- Circular progress indicator 
- Dial 
- Color Picker Bar
- Snackbar


#Samples

## 🤝 Contributing [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

Read our [contributing guide](doc/CONTRIBUTING.md) and let's build a better antd together.

We welcome all contributions. Please read our [CONTRIBUTING.md](doc/CONTRIBUTING.md) first. You can submit any ideas as [pull requests](https://github.com/adrianwitaszak/neumorph-ui/pulls) or as [GitHub issues](https://github.com/adrianwitaszak/neumorph-ui/issues). If you'd like to improve code, check out the Development Instructions and have a good time! :)

## ⭐ Author

[<img width="20" src="https://emojis.slackmojis.com/emojis/images/1643514782/7926/twitter.png?1643514782">](https://twitter.com/adrianwita) @adrianwita

[<img width="20" src="https://emojis.slackmojis.com/emojis/images/1643514111/711/linkedin.png?1643514111">](https://twitter.com/adrianwita) @adrian-witaszak


[<img width="400" src="https://helloimjessa.files.wordpress.com/2021/06/bmc-button.png">](https://www.buymeacoffee.com/adrianwitay)

## <img width="40" src="https://emojis.slackmojis.com/emojis/images/1643517461/34922/read-the-rules.gif?1643517461"> License
```
MIT License

Copyright (c) 2022 Adrian Witaszak

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```


Reference:
- [Android Jetpack Compose Color Picker](https://github.com/godaddy/compose-color-picker)
- [ComposeColorPicker](https://github.com/serbelga/ComposeColorPicker)
- [compose-neumorphism](https://github.com/sridhar-sp/compose-neumorphism)
