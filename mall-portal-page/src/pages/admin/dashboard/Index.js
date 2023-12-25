import React from "react";
import { Row, Col, Card, Statistic } from "antd";
import { ArrowUpOutlined } from "@ant-design/icons";

function Index() {
  return (
    <div>
      <Card title="数据汇总" bordered={false}>
        <Row gutter={8}>
          <Col span={8}>
            <Card title="新增用户" color="red">
              <Statistic
                title="新增用户"
                value="80"
                prefix={<ArrowUpOutlined />}
              ></Statistic>
            </Card>
          </Col>
          <Col span={8}>
            <Card title="总用户">
              <Statistic
                title="总用户"
                value="840"
                prefix={<ArrowUpOutlined />}
              ></Statistic>
            </Card>
          </Col>
          <Col span={8}>
            <Card title="今日订单">
              <Statistic
                title="今日用户"
                value="80"
                prefix={<ArrowUpOutlined />}
              ></Statistic>
            </Card>
          </Col>
        </Row>
      </Card>
      <Card title="其他统计" bordered={false}></Card>
    </div>
  );
}

export default Index;
