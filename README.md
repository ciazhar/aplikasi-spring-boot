#APLIKASI PESERTA PELATIHAN

#Setup Development Environment
- JDK 1.8
- Tomcat Sever
- MySQL

#Teknologi yang digunakan
- Spring Boot
- Spring Data
- Spring Security

#Setup Project
- Buka browser masukkan url
  ```
    http://start.spring.io/
  ```
- Masukkan data, sesuaikan dengan dependency yang dibutuhkan(web, thymeleaf) lalu download
- Add project ke text editor

Note :
Pada Project tersebut terdapat 3 buah file utama yaitu :
- pom.xml (konfigurasi maven)
- application.properties (konfigurasi database)
- Application.java (main class)

#Buat Halaman Web Sederhana
- Buat simple controller(main/java/domain/Controllers/HaloController.java)
  ```
    @Controller
    public class HaloController{
      ///mapping url
      @RequestMapping("/")
      public String halo(){
        return "index";///return ke view-nya, yaitu index.html
      }
    }
  ```
- Buat File Html(main/resources/templates/index.html)

#Memulai CRUD
- Tambahkan dependency (pom.xml)
  ```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
  ```
- Buat Model Peserta(main/java/domain/entity/Peserta.java)
  ```
    @Entity
    public class Peserta{
      /*id menggunakan string karena nanti akan menggunakan UUID,
      sehingga id digenerate secara unique.*/
      @Id @GeneratedValue(generator = "uuid")
      @GenericGenerator(name = "uuid", strategy = "uuid2")
      private String id;

      /*
        not empty just for string
        not null, not empty, size buat validasi
      */
      @Column(nullable=false)
      @NotNull
      @NotEmpty
      @Size(min = 1, max = 150)
      private String nama;

      @Column(nullable=false,unique=true)
      @Email
      @NotNull
      @NotEmpty
      private String email;

      @Column(nullable=false,unique=true)
      @NotNull
      @NotEmpty
      private String noHp;

      @Version
      @Column(columnDefinition= "integer DEFAULT 0")
      private Integer version;
  ```
- Generate Getter and setter(main/java/domain/entity/Peserta.java)
  ```
    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getNama() {
      return nama;
    }

    public void setNama(String nama) {
      this.nama = nama;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getNoHp() {
      return noHp;
    }

    public void setNoHp(String noHp) {
      this.noHp = noHp;
    }

    public Integer getVersion() {
      return version;
    }

    public void setVersion(Integer version) {
      this.version = version;
    }
  ```
#Setting Database
- Mapping database (main/resources/application.properties)
  ```
    spring.datasource.url=jdbc:mysql://localhost/pelatihan
    spring.datasource.username=pelatihanuser
    spring.datasource.password=pelatihanpasswd
  ```
- Bikin Schema (CLI)
  ```
    mysql -u root -p
    grant all on pelatihan.* to pelatihanuser@localhost identified by 'pelatihanpasswd'
    create database pelatihan;
  ```
- Meggunakan mysql (CLI)
  ```
    use pelatihan;			///menggunakan tabel
    show tables;			///memperlihatkan tabel dan datanya
    show create table peserta \G	///memperlihatkan detil tabel
    drop table peserta;		///redeploy tabel
  ```
#Test Database
  ```
    insert into peserta (id, nama, email, tanggal_lahir)
    values ('aa1', 'Peserta Test 001', 'peserta.test.001@gmail.com', '2011-01-01');
  ```
#Buat tampilan UI Peserta
- Bikin Dao (main/java/domain/dao/PesertaDao.java)
  Note :
  Dao ini file yang berupa interface yang akan dimplementasi ke class service
  ```
    public interface PesertaDao{
        List <Peserta> daftarPeserta();
        Peserta saveOrUpdate (Peserta peserta);
        Peserta getIdPeserta(String id);
        void delete(String id);
    }
  ```
- Bikin class service(main/java/domain/services/PesertaService.java)
  ```
  @Service
  public class PesertaService implements PesertaDao {

      @Autowired
      private EntityManagerFactory entityManagerFactory;

      @Override
      public List<Peserta> daftarPeserta() {
          EntityManager entityManager = entityManagerFactory.createEntityManager();
          return entityManager.createQuery("from Peserta", Peserta.class).getResultList();
      }

      @Override
      public Peserta saveOrUpdate(Peserta peserta) {
          EntityManager entityManager = entityManagerFactory.createEntityManager();
          entityManager.getTransaction().begin();
          Peserta saved = entityManager.merge(peserta);///merge berfungsi untuk mengecek apakah data udah ada di database, jika belum create, jika sudah update
          entityManager.getTransaction().commit();
          return saved;
      }

      @Override
      public Peserta getIdPeserta(String id) {
          EntityManager entityManager = entityManagerFactory.createEntityManager();
          return entityManager.find(Peserta.class,id);

      }

      @Override
      public void delete(String id) {
          EntityManager entityManager = entityManagerFactory.createEntityManager();
          entityManager.getTransaction().begin();
          entityManager.remove(entityManager.find(Peserta.class,id));
          entityManager.getTransaction().commit();
      }

  ```
- Bikin class controller(main/java/domain/controllers/PesertaController.java)
  ```
    @Controller
    public class PesertaController{

        @Autowired
        private PesertaDao pesertaDao;

        @RequestMapping("/peserta")
        public String listPeserta(Model model){
            model.addAttribute("peserta", pesertaDao.daftarPeserta());
            return "/peserta/listPeserta";
        }

        @RequestMapping(value = "/peserta/create", method = RequestMethod.GET)
        public String tampilkanForm(Model model){
            model.addAttribute("peserta",new Peserta());
            return "/peserta/formPeserta";
        }

        @RequestMapping(value = "/peserta/create", method = RequestMethod.POST)
        public String saveForm(Model model, Peserta peserta){
            model.addAttribute("peserta", pesertaDao.saveOrUpdate(peserta));
            return "redirect:/peserta";
        }

        @RequestMapping(value = "/peserta/edit/{id}",method = RequestMethod.GET)
        public String editForm(@PathVariable String id, Model model){
            model.addAttribute("peserta",pesertaDao.getIdPeserta(id));
            return "/peserta/formPeserta";
        }

        @RequestMapping("/peserta/hapus/{id}")
        public String deletePeserta(@PathVariable String id){
            pesertaDao.delete(id);
            return "redirect:/peserta";
        }
    }
  ```
- Bikin UI Peserta(main/resources/peserta/listPeserta.html, main/resources/peserta/formPeserta.html)

#Bikin Otorisasi Login
- Tambahkan dependency (pom.xml)
  ```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
  ```
- Bikin KonfigurasiSecurity (main/java/domain/config/KonfigurasiSecurity.java)
  ```
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter{
        private static final String SQL_LOGIN
                = "SELECT username,password, enable " +
                "FROM s_users WHERE username = ?";

        private static final String SQL_PERMISSION
                = "SELECT u.username, r.nama as authority " +
                "FROM s_users u " +
                "JOIN s_user_role ur on u.id = ur.id_user " +
                "JOIN s_roles r on ur.id_role = r.id " +
                "WHERE u.username = ?";

        @Autowired
        private DataSource dataSource;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

            //setting security non database
            auth
                    .inMemoryAuthentication()
                    .withUser("ciazhar")
                    .password("123")
                    .roles("apa");

            ///Setting security database
            /*auth
                    .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(SQL_LOGIN)
                    .authoritiesByUsernameQuery(SQL_PERMISSION);*/
        }

        ///konfigurasi web mana yg boleh diakses admin staf user dll
        protected void configure(HttpSecurity http) throws Exception{
            http
                    .authorizeRequests()
                    .antMatchers("/css/**","/js/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                    .logout();
        }
    }
  ```
- Bikin UI Login(main/resources/login.html)
