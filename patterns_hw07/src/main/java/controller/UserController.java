package controller;

@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String allUsers(Model model,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size
    ) {
        logger.info("Filtering by name: {} email: {}", name, email);

        Specification<User> spec = UserSpecification.trueLiteral();

        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, size.orElse(5));
        int totalPages = userRepository.findAll(spec, pageRequest).getTotalPages();

        if (name != null && !name.isEmpty()) {
            spec = spec.and(UserSpecification.loginLike(name));
        }

        if (email != null && !email.isEmpty()) {
            spec = spec.and(UserSpecification.loginLike(email));
        }

        model.addAttribute("usersPage", userRepository.findAll(spec, pageRequest));
        model.addAttribute("totalPages", totalPages);
        return "users";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/update")
    public String updateUser(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user";
        }

        // TODO реализовать проверку повторного ввода пароля.
        // TODO Исправить метод bindingResult.rejectValue();

        userRepository.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
