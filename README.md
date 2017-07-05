# JaPaMiRo
Das Mobile App Engineering Projekt der Gruppe JaPaMiRo

Die Dependencies in der build.gradle von der App müssen lauten 
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.3.1'
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support:support-v4:25.3.1'
    testCompile 'junit:junit:4.12'
}

