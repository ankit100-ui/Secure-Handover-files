async function post(u, d) {
    let r = await fetch(u, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(d)
    });
    return r.json();
}

document.getElementById("f").addEventListener("submit", async e => {
    e.preventDefault();

    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const nameInput = document.getElementById("name");
    const msg = document.getElementById("msg");

    let data = {
        email: emailInput.value.trim(),
        password: passwordInput.value
    };

    if (location.pathname.endsWith("register.html")) {
        data.name = nameInput.value.trim();
    }

    let x = await post(
        location.pathname.endsWith("register.html")
            ? "/api/auth/register"
            : "/api/auth/login",
        data
    );

    msg.textContent = x.success ? "Success!" : x.message;

    if (x.success) {
        if (x.token) {
            localStorage.setItem("token", x.token);
        }

        setTimeout(
            () => location.href = x.token ? "dashboard.html" : "login.html",
            500
        );
    }
});