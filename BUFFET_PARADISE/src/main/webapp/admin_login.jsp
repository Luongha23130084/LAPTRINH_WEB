<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login | Buffet Paradise</title>
    <link rel="stylesheet" href="css/layout.css">
    <style>
        body {
            background: #ffffff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
            font-family: Arial;
        }

        .login-box {
            background: #ffffff;
            padding: 40px;
            width: 360px;
            border-radius: 10px;
            border: 1px solid #e0e0e0;
            box-shadow: 0 8px 25px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #c9a227; /* gold nhẹ */
            margin-bottom: 25px;
        }

        input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input:focus {
            outline: none;
            border-color: #c9a227;
        }

        button {
            width: 100%;
            padding: 12px;
            background: #c9a227;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            color: #000;
            margin-top: 10px;
        }

        button:hover {
            background: #b8961e;
        }

        .error {
            color: #ff4d4d;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="login-box">
    <h2>🔒 ADMIN LOGIN</h2>

    <form action="admin-login" method="post">
        <input type="email" name="email" placeholder="Admin Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">LOGIN</button>
    </form>

    <p class="error">${error}</p>
</div>

</body>
</html>
