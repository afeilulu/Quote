import com.hsdp.Role
import com.hsdp.User
import com.hsdp.UserRole

class BootStrap {

    def init = { servletContext ->
        Role admin = new Role("ROLE_ADMIN").save()
        User user = new User("admin", "pass").save()
        UserRole.create(user, admin)

        Role anonymous = new Role("ROLE_ANONYMOUS").save()
        User guest = new User("guest", "guest").save()
        UserRole.create(guest, anonymous)
    }
    def destroy = {
    }
}
