# User Data Flow with Clean Architecture in Android

Bu proje, Android’de **Clean Architecture** prensiplerine göre **User** verisinin nasıl yönetildiğini göstermektedir. Uygulama, kullanıcı verisini bir veritabanından alır ve UI’da görüntülemek üzere gerekli işlemleri gerçekleştirir. Bu proje örneğinde **User** verisi üzerinden veri akışının her aşaması ele alınmıştır.

## Proje Yapısı

Proje, **Clean Architecture** ilkelerine uygun olarak üç ana katmanda organize edilmiştir:

- **Data Katmanı**: Veritabanı erişim işlemleri ve verinin Entity yapısında saklanmasını sağlar.
- **Domain Katmanı**: İş mantığı (business logic) ve Use Case’leri içerir.
- **Presentation Katmanı**: ViewModel ve UI bileşenlerini içerir.

### Kullanılan Katmanlar ve Veri Akışı

Aşağıda, `User` verisinin kaynaktan UI’a ulaşana kadar geçtiği aşamalar ve her katmanda yapılan işlemler açıklanmıştır.

### Veri Akışı Adımları

1. **Data Katmanı**:
   - **UserEntity**: Veritabanında `User` verisini temsil eden ve Room tarafından yönetilen Entity sınıfıdır.
   - **UserDao**: `UserEntity` verisini veritabanında yönetmek için gerekli işlemleri (CRUD) tanımlar.
   - **UserRepository**: `UserDao`’yu kullanarak **Entity**'yi **Domain Model**'e dönüştürür ve üst katmanlara veri sağlar. `UserRepository`, veri akışının ilk noktasını oluşturur ve Data katmanının Domain ile olan bağlantısını sağlar.

2. **Domain Katmanı**:
   - **User Model**: Domain Model, uygulama içinde `User` verisini temsil eden iş mantığına uygun yapıdır. `UserEntity`’den farklı olarak iş mantığı katmanında kullanılır.
   - **Use Case**: Her bir işlem için bağımsız olarak `GetUserUseCase`, `InsertUserUseCase`, `UpdateUserUseCase` ve `DeleteUserUseCase` gibi Use Case sınıfları oluşturulmuştur. Use Case’ler iş mantığını tek bir yerde toplar ve `UserRepository` ile etkileşime geçerek veri işlemlerini gerçekleştirir.

3. **Presentation Katmanı**:
   - **UserViewModel**: `UserViewModel`, **UI** ile **Use Case**'ler arasında bir köprü görevi görür. UI’dan gelen kullanıcı taleplerini alır ve gerekli **Use Case**'leri çağırarak veriyi işler. Örneğin, `loadUserProfile(userId)` fonksiyonu ile `GetUserUseCase`’i çağırarak kullanıcı verisini alır.
   - **UI (Jetpack Compose)**: `UserProfileScreen` adlı **Composable** ekran, `UserViewModel`'deki `userProfile` StateFlow’undan veriyi gözlemler ve kullanıcı bilgilerini ekrana yansıtır.

İsterseniz bu konudaki [medium yazımı](https://medium.com/@hsdfiratuniversity/modern-android-geliştirmede-clean-architectureın-önemi-5823923f4e69) da inceleyebilirsiniz:

