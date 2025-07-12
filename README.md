# Kur Uygulaması - Android Uygulaması

![Java](https://img.shields.io/badge/dil-Java-orange.svg)
![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![Lisans](https://img.shields.io/badge/lisans-MIT-blue.svg)

Bu proje, Java dili kullanılarak geliştirilmiş, güncel döviz kurlarını takip etmek için tasarlanmış bir mobil uygulamadır. Kullanıcılara anlık ve doğru kur bilgisi sunmayı amaçlar.

---

## 📖 İçindekiler

- [Proje Hakkında](#-proje-hakkında)
- [✨ Temel Özellikler](#-temel-özellikler)
- [🛠️ Teknoloji Yığını](#-teknoloji-yığını)
- [🚀 Başlarken](#-başlarken)
  - [Gereksinimler](#gereksinimler)
  - [Kurulum Adımları](#kurulum-adımları)
- [🤝 Katkıda Bulunma](#-katkıda-bunma)
- [👤 İletişim](#-iletişim)

---

## 📝 Proje Hakkında

**Kur Uygulaması**, kullanıcıların farklı para birimlerinin anlık değerlerini kolayca takip edebilmeleri için geliştirilmiş bir Android uygulamasıdır. Güvenilir bir API üzerinden çektiği verilerle, popüler para birimlerinin Türk Lirası ve diğer para birimleri karşısındaki değerlerini listeler. Uygulamanın temel amacı, finansal veriye hızlı ve kolay erişim sağlamaktır.

---

## ✨ Temel Özellikler

- **Anlık Kur Bilgisi:** Döviz kurlarını güvenilir bir kaynaktan anlık olarak çeker ve gösterir.
- **Geniş Para Birimi Desteği:** Popüler para birimlerini destekler.
- **Döviz Çevirici:** Belirli bir miktar parayı farklı bir para birimine çevirmeyi sağlar.
- **Kullanıcı Dostu Arayüz:** Verilerin kolayca okunabildiği sade ve modern bir arayüz.

---

## 🛠️ Teknoloji Yığını

Bu projenin geliştirilmesinde kullanılan temel teknolojiler ve kütüphaneler aşağıda listelenmiştir.

- **Programlama Dili:** [**Java**](https://www.java.com/)
- **Platform:** [**Android SDK**](https://developer.android.com/)
- **Geliştirme Ortamı (IDE):** [**Android Studio**](https://developer.android.com/studio)
- **API İletişimi:** Harici bir kur API'si ile iletişim kurmak için **HttpURLConnection** veya **Retrofit/Volley** gibi kütüphaneler.
- **JSON Ayrıştırma (Parsing):** API'den gelen verileri işlemek için **org.json** kütüphanesi veya **GSON/Jackson**.
- **Asenkron İşlemler:** Ağ isteklerini arka planda yönetmek için **AsyncTask** veya **Threadler**.
- **UI Bileşenleri:** Verileri listelemek için **RecyclerView** veya **ListView**.

---

## 🚀 Başlarken

Projeyi yerel makinenizde kurup çalıştırmak için aşağıdaki adımları izleyebilirsiniz.

### Gereksinimler

- [**Android Studio**](https://developer.android.com/studio) (En son sürüm önerilir)
- **JDK** (Java Development Kit) 11 veya üzeri

### Kurulum Adımları

1.  **Depoyu Klonlayın:**
    ```bash
    git clone https://github.com/KeremOgzhn/Kur_Uygulamasi.git
    ```
2.  **Projeyi Android Studio'da Açın:**
    - Android Studio'yu başlatın ve `File > Open` menüsünden klonladığınız proje klasörünü seçin.
3.  **API Anahtarını Yapılandırın (Gerekliyse):**
    - Proje harici bir API kullanıyorsa, gerekli API anahtarını kod içerisinde belirtilen yere eklemeniz gerekebilir.
4.  **Bağımlılıkları Yükleyin:**
    - Android Studio, projeyi açtığınızda `build.gradle` dosyasındaki bağımlılıkları otomatik olarak indirecektir.
5.  **Uygulamayı Derleyin ve Çalıştırın:**
    - Bir Android emülatör veya fiziksel bir cihaz seçerek **Run 'app'** butonuna tıklayın.

---

## 🤝 Katkıda Bulunma

Katkılarınız projeyi daha iyi hale getirecektir! Katkıda bulunmak için:

1.  Bu depoyu **Fork**'layın.
2.  Yeni bir dal oluşturun (`git checkout -b ozellik/YeniOzellik`).
3.  Değişikliklerinizi **Commit**'leyin (`git commit -m 'Yeni bir özellik eklendi'`).
4.  Dalınızı **Push**'layın (`git push origin ozellik/YeniOzellik`).
5.  Bir **Pull Request** açın.

---

## 👤 İletişim

Kerem Oğuzhan - [@KeremOgzhn](https://github.com/KeremOgzhn)

Proje Linki: [https://github.com/KeremOgzhn/Kur_Uygulamasi](https://github.com/KeremOgzhn/Kur_Uygulamasi)