import {Card, Avatar, List, Typography, Button} from "antd";
import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {
    decrement,
    increment,
    noticeSlice,
    readAll,
    selectCount,
    selectIsAllRead, setNotice
} from "../../../store/actions/noticeSlice";

function Index(props) {
    const data = [
        "Racing car sprays burning fuel into crowd.",
        "Japanese princess to wed commoner.",
        "Australian walks 100km after outback crash.",
        "Man charged over missing wedding girl.",
        "Los Angeles battles huge wildfires.",
    ];

    const count = useSelector(selectCount)
    const isAllRead = useSelector(selectIsAllRead)
    const dispatch = useDispatch()
    // 初始化通知数量
    useEffect(() => {
        // 这里可以做异步接口请求
        dispatch(setNotice({count:50}))
    }, []);
    return (
        <Card title="通知中心" extra={<Button size="small" onClick={() => dispatch(readAll())}>全部标记为已读</Button>}>
            {count}
            <Button size="small" onClick={() => dispatch(increment())}>+1</Button>
            <List
                header={<div>Header</div>}
                footer={<div>Footer</div>}
                bordered
                dataSource={data}
                renderItem={(item) => (
                    <List.Item style={{display: "flex", alignContent: "space-between"}}>
                        <Typography.Text mark>[ITEM]</Typography.Text> {item}
                        <Button size="small" onClick={() => dispatch(decrement())}>标记为已读</Button>
                    </List.Item>
                )}
            />
        </Card>
    );
}

export default Index;
