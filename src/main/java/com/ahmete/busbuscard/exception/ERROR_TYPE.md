# UYGULAMA HATALARI

## GENEL HATALAR

### 500
* Sunucu hatası
### 400
* Girilen parametlerelerin hatalı olma durumu

## SERVICE KATMANI - USERSERVICE(1)
* 1 X X X
### 1001
* Kullanıcı bulunamadı
### 1002
* TC kimlik hali hazırda var hatası!



## SERVICE KATMANI - CARD İŞLEMLERİ (5)
* 5 X X X
### 5001
* Yetersiz bakiye hatası
### 5002
* Kart bulunamadı hatası
### 5003
* Kartlar tükendi hatası
### 5004 
* Fazla kart istek hatası
### 5005
* Gecersiz kart hatası

## SERVICE KATMANI - TRANSACTIONSERVICE (6)
* 6 X X X 
### 6001
* Tarih hatası

* 6 X X 2 (Transaction Bakiye İşlemleri)
### 6002
* Ulaşım kartına yetersiz bakiyeden para yükleyememe hatası
### 6012
* Ulaşım kartına minimum kotadan az para yükleme hatası (Örn: 20 Tlden az yüklenemez gibi)
