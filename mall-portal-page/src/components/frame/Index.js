import React from "react";
import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined,
  DownOutlined,
  SmileOutlined,
} from "@ant-design/icons";
import {
  Breadcrumb,
  Layout,
  Menu,
  theme,
  Dropdown,
  Space,
  Avatar,
  message,
  Badge,
} from "antd";
import { adminRoutes } from "../../routes/index";
import { useHistory, withRouter } from "react-router-dom";
import logo from "./logo.png";
import "./frame.css";
import { clearToken } from "../../utils/auth";
import {useSelector} from "react-redux";
import {selectCount, selectIsAllRead} from "../../store/actions/noticeSlice";

const { Header, Content, Sider } = Layout;
const routes = adminRoutes.filter((route) => route.isShow);
const items = [
  {
    key: "notice",
    label: "通知中心",
  },
  {
    key: "2",
    label: "设置",
  },
  {
    key: "logout",
    label: "退出",
  },
];
function Index(props) {
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  const history = useHistory();

  const onClick = ({ key }) => {
    switch (key) {
      case "logout":
        {
          clearToken();
          history.push("/login");
        }
        break;
      case "notice":
        {
          history.push("/admin/notice");
        }
        break;
      default:
        message.info(key);
        break;
    }
  };
  return (
    <Layout>
      <Header className="header">
        <div className="logo">
          <img src={logo} alt="logo"></img>
        </div>
        <Dropdown menu={{ items, onClick }}>
          <a>
            {/* <a onClick={(e) => e.preventDefault()}> */}
            <Space>
              <Avatar>U</Avatar>
              <Badge dot={!useSelector(selectIsAllRead)}>
                <span>管理员</span>
              </Badge>
              <DownOutlined />
            </Space>
          </a>
        </Dropdown>
      </Header>
      <Layout>
        <Sider
          width={200}
          style={{
            background: colorBgContainer,
          }}
        >
          <Menu
            mode="inline"
            defaultSelectedKeys={["/admin/dashboard"]}
            // defaultOpenKeys={['sub1']}
            style={{
              height: "100%",
              borderRight: 0,
            }}
            items={routes.map((route) => {
              return {
                key: route.path,
                icon: route.icon,
                label: route.title,
                onClick: (item, key, keyPath, domEvent) => {
                  history.push(route.path);
                },
              };
            })}
          ></Menu>
        </Sider>
        <Layout
          style={{
            padding: "0 24px 24px",
          }}
        >
          <Breadcrumb
            style={{
              margin: "16px 0",
            }}
          >
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>App</Breadcrumb.Item>
          </Breadcrumb>
          <Content
            style={{
              padding: 24,
              margin: 0,
              minHeight: 280,
              background: colorBgContainer,
            }}
          >
            {props.children}
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
}

export default Index;
