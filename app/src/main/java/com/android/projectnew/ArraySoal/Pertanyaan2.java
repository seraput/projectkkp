package com.android.projectnew.ArraySoal;

public class Pertanyaan2 {
    private String textQuestion[] = {
            "Kebanyakan burung dapat terbang. Burung unta adalah juga seekor burung. Jadi :",
            "Semua Mahasiswa Pertanian UMJ lulus tepat pada waktunya. Sebagian Mahasiswa Pertanian UMJ adalah Mahasiswa Program Perkuliahan Karyawan (P2K). Jadi :",
            "Semua donor harus berbadan sehat. Sebagian donor darah memiliki golongan darah O, jadi ...",
            "Di Jakarta telah banyak dibangun gedung bertingkat. Hotel-hotel dan pasar-pasar yang baru dibangun di negara kita banyak yang bertingkat. Pak Sarlito baru datang ke Jakarta dan tinggal di sebuah hotel. Pak Sarlito sudah merencanakan untuk melihat dari dekat gedung-gedung yang bertingkat. jadi...",
            "Hanya jika berbakat dan bekerja keras, seorang atlet dapat sukses sebagai atlet profesional. Berikut adalah kesimpulan yang secara logis dapat ditarik dari pernyataan di atas:",
            "Murid yang pandai dalam matematika lebih mudah belajar bahasa. Orang yang tinggal di negara asing lebih lancar berbicara dalam bahasa yang dipakai di negara tersebut. Tati lancar berbicara dalam bahasa Inggris. Jadi :",
            "Sarjana yang lulus dengan predikat cum laude harus memiliki indeks prestasi di atas 3,5. Beberapa mahasiswa yang menjadi sarjana lulus dengan indeks prestasi di bawah 3,5. Kesimpulan pernyataan di atas adalah :",
            "Pengurus koperasi seharusnya berjiwa sosial. Sebagian ketua rukun tetangga pernah menjadi pengurus koperasi. Jadi :",
            "Di suatu kelas yang terdiri atas 40 siswa, memiliki minat terhadap mata pelajaran berikut: tak ada siswa yang menyenangi matematika juga menyenangi pelajaran lain. Hanya 4 orang siswa yang menyenangi fisika tetapi tidak mata pelajaran lain. 15 orang menyenangi IPS dan 5 orang diantaranya juga menyenangi IPA. Jika jumlah siswa yang menyenangi biologi 7 orang maka dapat disimpulkan...",
            "Jika A = B maka A tidak sama dengan C. Bila P = A, maka....",
            "Bila kendaraan lewat jalan tol harus membayar; Sebagian kendaraan tak punya uang, jadi;",
            "Bila S lewat P harus tambah H. Sebagian S tak punya H. Jadi :",
            "Apabila sebagian orang yakin adanya kehidupan setelah kematian, sebagiannya lagi tidak yakin adanya kehidupan setelah kematian, dan sebagian lainnya sangat yakin bahwa tidak akan ada kehidupan setelah kematian, maka....",
            "Beberapa benda yang memuai adalah logam. Semua logam adalah benda padat. Jadi :",
            "Tidak ada dua gaun rancangan desainer X yang memiliki model yang sama. Gaun A dan B adalah rancangan desainer X. Jadi :",
            "Semua murid yang mengikuti ujian tidak menggunakan kalkulator. Sebagian murid yang ujian mengenakan jam tangan. Jadi :",
            "Semua U bukan B. Sebagian U adalah C. Jadi :",
            "Akhmad adalah pekerja. Akhmad menjadi buruh di sebuah pabrik. Banyak di antara buruh-buruhnya yang malas bekerja. Badrun adalah teman Akhmad. Jadi : .",
            "Dalam sebuah kelas, sebagian besar murid pandai berenang, sebagian besar juga pandai mendayung. Jadi :",
            "Semua mahasiswa yang berdasi. Sebagian mahasiswa berjas. Jadi :",
            "Semua dosen adalah pegawai negeri. Sebagian dosen adalah seniman. Yang tidak cocok dengan pernyataan tersebut adalah....",
            "Christina bersekolah di SMA Negeri VII. Tema-teman sekelasnya berjumlah 29 orang. Junus adalah tetangga Christina. Junus tahu benar bahwa ada teman Christina yang tidak lulus ujian penghabisan. Jadi :",
            "Semua Y adalah P. Sebagian Y adalah R. Jadi :",
            "Semua kertas gambar sangat berguna. Sebagian kertas yang sangat berguna harganya murah. Jadi :",
            "Setiap kota yang memiliki pusat hiburan mempunyai ciri rawan kejahatan. Pusat hiburan menyebabkan adanya keramaian yang menarik para penjahat. Sebgian penjahat adalah residivis. Manakah pernyataan di bawah ini yang tidak dapat disimpulkan dari pernyataan di atas?"


    };
    //jawaban

    private String multipleChoiche[][] = {
            {"Burung unta dapat terbang", "Burung unta memang tidak dapat terbang", "Burung unta belum tentu dapat terbang", "Jawaban a,b, dan c ketiga-tiganya salah"},//1
            {"Semua Mahasiswa Program Perkuliahan Karyawan (P2K)", "Sebagian Mahasiswa Pertanian UMJ lulus tidak tepa", "Mahasiswa Pertanian UMJ yang lulus tepat pada wak", "Sebagian Mahasiswa Pertanian UMJ adalah Mahasiswa"},//2
            {"Sebagian orang yang bergolongan darah O dan menja", "Semua donor harus memiliki golongan darah O dan b", "Semua donor darah yang memiliki golongan darah O", "Yang berbadan sehat adalah yang memiliki golongan"},//3
            {"Pak Sarlito menginap di hotel yang bertingkat", "Tidak ada hotel yang bertingkat di Jakarta", "Di Jakarta banyak hotel-hotel yang bertingkat", "Mungkin Pak Sarlito menginap di hotel yang tidak"},//4
            {"Jika seorang atlet berbakat dan bekerja keras, ma", "Jika seorang atlet tidak sukses sebagai atlet pro", "Jika seorang atlet tidak sukses sebagai atlet pro", "Jika seorang atlet tidak berbakat atau tidak beke"},//5
            {"Mungkin Tati bisu.", "Mungkin Tati tidak pernah tinggal diluar negeri.", "Tidak mungkin Tati pernah tinggal di luar negeri.", "Tidak mungkin Tati pandai dalam matematik."},//6
            {"Semua mahasiswa tidak lulus dengan predikat cum l", "Semua mahasiswa yang menjadi sarjana lulus dengan", "Semua mahasiswa yang menjadi sarjana memiliki ind", "Beberapa mahasiswa yang menjadi sarjana lulus den"},//7
            {"Ketua rukun tetangga itu selalu berjiwa sosial.", "Semua orang yang pernah menjadi ketua rukun tetan", "Sebagian ketua rukun tetangga seluruhnya berjiwa", "Semua pengurus koperasi berjiwa sosial."},//8
            {"Jumlah siswa yang menyenangi matematika paling ba", "Jumlah siswa yang menyenangi IPS paling banyak", "Jumlah siswa yang menyenangi IPA paling banyak", "Jumlah siswa yang menyenangi matematika sama deng"},//9
            {"Bila P = C maka P= B", "Bila P = C maka P tidak sama dengan A", "Bila P = C maka tidak P tidak sama dengan B", "Bila P = C maka P = A = B"},//10
            {"Semua kendaraan tidak lewat jalan tol.", "Semua kendaraan lewat jalan tol.", "Sebagian kendaraan tak punya uang.", "Sebagian kendaraan tidak lewat jalan tol."},//11
            {"Semua S tidak lewat P", "Sebagian S tidak lewat P", "Sebagian S tak punya H", "Semua S tidak lewat P dan tak lewat H"},//12
            {"Sebagian orang yakin akan ada kematian.", "Tidak semua orang yakin akan ada kematian.", "Semua orang meyakini adanya kematian.", "Mengenai kehidupan setelah kematian, tidak semua"},//13
            {"Hanya logam yang memuai merupakan benda padat.", "Benda yang bukan logam tidak memuai.", "Beberapa benda padat dapat memuai.", "Benda padat dapat memuai."},//14
            {"Sebagian gaun rancangan desainer X.", "Semua desainer merancang gaun dengan mode seperti", "Gaun A dan B tidak memiliki mode yang sama.", "Gaun yang modenya tidak sama berasal dari desaine"},//15
            {"Semua murid yang ujian mengenakan jam tangan.", "Sementara murid yang ujian tidak mengenakan jam t", "Semua murid yang ujian tidak menggunakan kalkulat", "Sebagian murid yang ujian mengenakan jam tangan d"},//16
            {"Sebagian U adalah C bukan B", "Semua U bukan B dan C", "Sebagian U bukan C adalah B", "Semua U adalah C"},//17
            {"Akhmad itu malas.", "Badrun itu malas.", "Badrun mungkin teman sekerja Akhmad.", "Teman-teman Badrun semuanya rajin."},//18
            {"Sebagian besar murid tidak pandai berenang dan ti", "Sebagian besar murid pandai berenang saja", "Sebagian tertentu dari murid-murid itu pandai ber", "Jawaban a, b, dan c ketiga-tiganya salah"},//19
            {"Sebagian mahasiswa bersepatu", "Sebagian mahasiswa berjas dan bersepatu", "Sebagian mahasiswa berdasi dan berjas", "Sebagian mahasiswa bersepatu tapi tak berjas"},//20
            {"Sebagian seniman adalah pegawai negeri", "Sebagian pegawai negeri adalah dosen", "Sebagian seniman adalah dosen", "Semua seniman adalah pegawai negeri"},//21
            {"Mungkin semua teman sekelas Christina tidak lulus", "Mungkin semua teman sekelas Christina tidak lulus", "Tidak mungkin ada teman Christina yang lulus.", "Tidak mungkin semua teman Christina dikenal oleh"},//22
            {"Semua Y yang bukan P adalah R", "Semua Y yang bukan R adalah P", "Semua R yang bukan Y adalah P", "Sebagian Y yang bukan R adalah P"},//23
            {"Semua kertas gambar harganya murah.", "Semua kertas yang harganya murah adalah kertas ga", "Sebagian kertas harganya murah.", "Kertas yang murah sangat berguna"},//24
            {"Semua penjahat adalah residivis.", "Semua pusat hiburan menarik penjahat.", "Setiap kota mempunyai ciri rawan kejahatan.", "Penjahat tertarik adanya keramaian."}//25



    };
    private String mCorretAnswer[] = {
            "Burung unta belum tentu dapat terbang",//1
            "Semua Mahasiswa Program Perkuliahan Karyawan (P2K)",//2
            "Semua donor darah yang memiliki golongan darah O",//3
            "Mungkin Pak Sarlito menginap di hotel yang tidak",//4
            "Jika seorang atlet berbakat dan bekerja keras, ma",//5
            "Mungkin Tati tidak pernah tinggal diluar negeri.",//6
            "Beberapa mahasiswa yang menjadi sarjana lulus den",//7
            "Sebagian ketua rukun tetangga seluruhnya berjiwa",//8
            "Jumlah siswa yang menyenangi IPS paling banyak",//9
            "Bila P = C maka tidak P tidak sama dengan B",//10
            "Sebagian kendaraan tidak lewat jalan tol.",//11
            "Sebagian S tidak lewat P",//12
            "Mengenai kehidupan setelah kematian, tidak semua",//13
            "Beberapa benda padat dapat memuai.",//14
            "Gaun A dan B tidak memiliki mode yang sama.",//15
            "Sebagian murid yang ujian mengenakan jam tangan d",//16
            "Sebagian U adalah C bukan B",//17
            "Badrun mungkin teman sekerja Akhmad.",//18
            "Sebagian tertentu dari murid-murid itu pandai ber",//19
            "Sebagian mahasiswa berdasi dan berjas",//20
            "Semua seniman adalah pegawai negeri",//21
            "Mungkin semua teman sekelas Christina tidak lulus",//22
            "Semua Y yang bukan R adalah P",//23
            "Sebagian kertas harganya murah.",//24
            "Semua penjahat adalah residivis."//25

    };

    public int getLength()
    {
        return textQuestion.length;
    }
    public String getQuestion(int i)
    {
        String pertanyaan = textQuestion[i];
        return pertanyaan;
    }

    public String getChoice(int i, int num)
    {
        String jawaban = multipleChoiche[i][num-1];
        return jawaban;
    }
    public String getcorretAnswer(int i)
    {
        String jawabbenar = mCorretAnswer[i];
        return jawabbenar;
    }
}
