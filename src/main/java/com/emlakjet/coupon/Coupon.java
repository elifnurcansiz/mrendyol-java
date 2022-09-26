package com.emlakjet.coupon;

import java.util.Date;

public class Coupon {
    private final int couponId;
    private final Date validUntil; // kullanabileceği son tarih
    private final Date validFrom; //kullanabileceği ilk tarih
    private final CouponType couponType; // kuponun cinsi % cinsinden mi tl cinsinden mi olduğu
    private final int lowerLimit; //kullanıcının kuponu kullanabilmesi için min limit
    private final int maxDiscount; //kuponun max yapabileceği discount
    private final int discount; // kupon tipine göre tl cinsinden ya da % olarak yapıalcak discount

    public Coupon(int couponId, Date validUntil, Date validFrom, CouponType couponType, int lowerLimit, int maxDiscount, int discount) {
        this.couponId = couponId;
        this.validUntil = validUntil;
        this.validFrom = validFrom;
        this.couponType = couponType;
        this.lowerLimit = lowerLimit;
        this.maxDiscount = maxDiscount;
        this.discount = discount;
    }

    // kuponla ilgili işlemler kupon üzerinde olmalı
    // hangi classım varsa yapmak yapmak istediğim işlemler onun üstünde olmalı
    // kupon nesnesi oluştur kupon listesine ekle(hazır olacak) kupon listesi id ye göre arayıp kuponu alıp kuponun valid olup olmadığını kontrol edecek
    //valid kontrolü de kuponun üstündeki bir fonksiyonun üzerinde yapılacak fiyat ve tarihe göre ekstra tanımlamalara bak
    public boolean isValid(Date now, int money) {
        if (money < lowerLimit) {
            return false;
        }

        // alt limiti control edicez moneyle karşılaştıracağız kullanıcı tutarı <kupon alt limiti
        if (now.before(validFrom) || now.after(validUntil)) {
            return false;
        }

        //eğer arasında değilse dışındaysa(iki tarih) direk false olarak çevireceğiz fail fast(eğer birden fazla şartımız olacaksa sonrasında
        //bu şartların birisi bile sağlanmadığında çıkmasını sağlıyor

        return true;
    }

    public int getTotalDiscount(int price) {
        if (couponType.equals(CouponType.ABSOLUTE)) {
            return discount;

        } else if (couponType.equals(CouponType.RELATIVE)) {
            int realDiscount = (price / 100) * discount;
            return Math.min(realDiscount, maxDiscount);

        }
        return 0; //bir hata varsa discount yapma
    }

    public int getCouponId() {
        return couponId;
    }
}
