package mo.project.sellbook.repository;

import mo.project.sellbook.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    // Nếu "id" là tên trường khóa chính trong class Users:
    Optional<Users> findById(int id);

    // CHÚ Ý: findByUserId chỉ dùng nếu trong class Users bạn có một thuộc tính tên là "userId"
    // chứ không phải là "id". Nếu không Spring sẽ báo lỗi khi khởi chạy.
}