# login admin book inventory

<br>==========================================================================<br>

Dibuat menggunakan :
- Springboot
- Hibernate jpa
- Postgres
- Thymeleaf
- Lombok

<br>==========================================================================<br>

Feature :

Master Data :
- User
- Book Inventory

<br>==========================================================================<br>

Panduan instalasi :

Software yang diperlukan :
- Postgres server versi 9.6 atau lebih baru
- Java SDK versi 11
- IDE Intellij ultimate atau sejenis

1. Persiapan Database :
- Buka PGAdmin 4 lalu buat database baru (contoh : spring_sample)
- Restore database menggunakan file database .backup yang ada pada project

2. Build dan Jalankan program :
- Buka IDE Intellij lalu open project yang sudah di download.
- Download maven library
- ubah datasource url, datasource name dan password disesuaikan dengan koneksi database local.
- Running spring application melalui IDE atau build dan running jar melalui terminal.

3. Buka url http://localhost:8081/login kemudian masukkan username : admin dan password: admin