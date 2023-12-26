import React from "react";
import { Card, Space, Form, Input, Button } from "antd-mobile";
import { setToken } from "../../utils/authUtils";
import { loginApi } from "../../service/user";
import './login.css'

function Login(props) {
  // 表单验证通过后调用
  const onFinish = (values) => {
    console.log(values)
    loginApi(props, {
      username: values.username,
      password: values.password,
    })
  };
  return (
    <Card title="uuY Admin Sys" className="login-form-card">
      <Form
        className="login-form"
        layout="horizontal"
        onFinish={onFinish}
        // footer={
        //   <Button block type="submit" onClick={onFinish} color="primary" size="small" className='login-form-button'>
        //     登录
        //   </Button>
        // }
      >
        {/* <Form.Header>水平布局表单</Form.Header> */}
        <Form.Item
          name="username"
          label="用户名"
          rules={[{ required: true, message: "用户名不能为空" }]}
        >
          <Input onChange={console.log} placeholder="请输入姓名" />
        </Form.Item>
        <Form.Item name="password" label="密码">
          <Input placeholder='请输入密码' clearable type='password' />
        </Form.Item>
        <Form.Item>
          <Button block color='primary' size='small' type={'submit'} className="login-form-button">
            登录
          </Button>
          {/* Or <a href="">注册</a> */}
        </Form.Item>
      </Form>
    </Card>
  );
}

export default Login;
