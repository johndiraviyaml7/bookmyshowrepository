C:\Java\jdk1.8.0_342\bin>keytool -genkeypair -keyalg RSA -dname "cn=spaces,dc=example,dc=com" -alias bookKeyShow -keypass passBookPhrase4d34 -keystore oauth-bookmyshow-keystore.jks -storepass passphrasebookmyshow -validity 1064

Warning:
The JKS keystore uses a proprietary format. It is recommended to migrate to PKCS12 which is an industry standard format using "keytool -importkeystore -srckeystore oauth-bookmyshow-keystore.jks -destkeystore oauth-bookmyshow-keystore.jks -deststoretype pkcs12".

C:\Java\jdk1.8.0_342\bin>keytool -importkeystore -srckeystore oauth-bookmyshow-keystore.jks -destkeystore oauth-bookmyshow-keystore.jks -deststoretype pkcs12
Enter source keystore password:
keytool error: java.io.IOException: Keystore was tampered with, or password was incorrect

C:\Java\jdk1.8.0_342\bin>keytool -importkeystore -srckeystore oauth-bookmyshow-keystore.jks -destkeystore oauth-bookmyshow-keystore.jks -deststoretype pkcs12
Enter source keystore password:passphrasebookmyshow
Enter key password for <bookkeyshow>