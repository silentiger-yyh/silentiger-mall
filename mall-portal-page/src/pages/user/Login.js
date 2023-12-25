import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { Button, Checkbox, Form, Input, Card, Space, message } from "antd";
import { setToken } from "../../utils/auth";
import { loginApi } from "../../service/auth";
import "./login.css";

function Login(props) {
  // 表单验证通过后调用
  const onFinish = (values) => {
    // setToken(values.username);
    // props.history.push("/admin/dashboard");
    loginApi({
      username: values.username,
      password: values.password,
    })
      .then((res) => {
        if (res.status === 200) {
          console.log(res);
          setToken(res.data.data.token);
          props.history.push("/admin");
          message.success("登录成功");
        } else {
          message.error("登录失败");
        }
      })
      .catch((err) => {
        message.error(err.message);
      });
  };
  return (
    <Space direction="vertical" size={16}>
      <Card title="uuY Admin Sys" className="login-form-card">
        <Form
          name="normal_login"
          className="login-form"
          initialValues={{
            remember: true,
          }}
          onFinish={onFinish}
        >
          <Form.Item
            name="username"
            rules={[
              {
                required: true,
                message: "用户名!",
              },
            ]}
          >
            <Input
              prefix={<UserOutlined className="site-form-item-icon" />}
              placeholder="用户名"
            />
          </Form.Item>
          <Form.Item
            name="password"
            rules={[
              {
                required: true,
                message: "请输入密码!",
              },
            ]}
          >
            <Input
              prefix={<LockOutlined className="site-form-item-icon" />}
              type="password"
              placeholder="密码"
            />
          </Form.Item>
          <Form.Item>
            <Form.Item name="remember" valuePropName="checked" noStyle>
              <Checkbox>记住我</Checkbox>
            </Form.Item>

            {/* <a className="login-form-forgot" href="">
              忘记密码
            </a> */}
          </Form.Item>

          <Form.Item>
            <Button
              type="primary"
              htmlType="submit"
              className="login-form-button"
            >
              登录
            </Button>
            {/* Or <a href="">注册</a> */}
          </Form.Item>
        </Form>
      </Card>
    </Space>
  );
}

export default Login;
