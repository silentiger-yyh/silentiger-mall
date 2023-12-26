import React, { useEffect, useState } from "react";
import { Form, Card, Input, message, Button, Upload } from "antd";
import { createApi, getOneById, modifyOne } from "../../../service/products";
import { SERVER_URL } from "../../../config/globalConfig";
import { LoadingOutlined, PlusOutlined } from "@ant-design/icons";

function Edit(props) {
  // props.match.params.id存在的话表示修改,否怎为新增
  // console.log(props, "~~~~~");

  const [form] = Form.useForm();
  const [imageUrl, setImageUrl] = useState("");
  const [loading, setLoading] = useState(false);
  // 定义局部状态
  // const [currentData, setCurrentData] = useState({
  //   name: "111",
  //   price: 0,
  // });

  // 初始化当前组件获取当前组件的参数，并且需要使用useEffect，否则会一直发送请求
  useEffect(() => {
    if (props.match.params.id) {
      getOneById(props.match.params.id)
        .then((res) => {
          // console.log("数据", res.data);
          form.setFieldsValue(res.data); // 显示表单数据
          setImageUrl(res.data.coverImg); //显示图片
        })
        .catch((err) => {
          message.error(err.message);
        });
    }
  }, []);
  // 提交表单且数据验证成功后回调事件
  const onFinish = (val) => {
    if (props.match.params.id) {
      modifyOne(props.match.params.id, { ...val, coverImg: imageUrl })
        .then((res) => {
          message.success("保存成功");
        })
        .catch((err) => {
          message.error(err.message);
        });
    } else {
      createApi({ ...val, coverImg: imageUrl })
        .then((res) => {
          message.success("保存成功");
        })
        .catch((err) => {
          message.error(err.message);
        });
    }
  };
  // 提交表单且数据验证失败后回调事件
  const onFinishFailed = (msg) => {
    console.log(msg);
    // message.error(msg);
  };
  /**
   * 自定义校验规则
   * @param {*} rule
   * @param {*} value
   * @param {*} callback
   * @returns
   */
  const priceValidate = (rule, value, callback) => {
    // console.log(Number(value));
    // console.log(value * 1); //转数字
    if (Number(value)) {
      if (value * 1 > 100) {
        return Promise.reject("价格不能超过100");
        // callback("价格不能超过100");  //callback是旧的写法，浏览器console会提出警告：warning.js:19 Warning: `callback` is deprecated. Please return a promise instead.
      } else {
        return Promise.resolve();
        // callback(); // 这里不要callback好像也没啥毛病，但是提交没有反应
      }
    } else {
      return Promise.reject("请输入数字");
    }
  };
  const handleChange = (info) => {
    if (info.file.status === "uploading") {
      setLoading(true);
      return;
    }
    if (info.file.status === "done") {
      // 上传成功
      setLoading(false);
      setImageUrl(info.file.response.info);

      // Get this url from response in real world.
      // getBase64(info.file.originFileObj, (url) => {
      //   setLoading(false);
      //   setImageUrl(url);
      // });
    }
  };
  const uploadButton = (
    <div>
      {loading ? <LoadingOutlined /> : <PlusOutlined />}
      <div
        style={{
          marginTop: 8,
        }}
      >
        Upload
      </div>
    </div>
  );
  return (
    <Card title="商品编辑">
      <Form
        form={form}
        layout="horizontal"
        onFinish={onFinish} // 提交表单且数据验证成功后回调事件
        onFinishFailed={onFinishFailed} // 提交表单且数据验证失败后回调事件
        autoComplete="off"
        // initialValues={{ name: currentData.name }} // 这里只会在第一次进行渲染，后续数据的更新并不会造成重新渲染。所以，initialValues不适用于动态设置表单初始值。
      >
        <Form.Item
          name="name"
          label="商品名称"
          rules={[
            {
              required: true,
              message: "请输入商品名称",
              type: "string",
              whitespace: true, // 如果字段仅包含空格则校验不通过，只在 type: 'string' 时生效
            },
          ]}
        >
          <Input placeholder="请输入商品名称" />
        </Form.Item>
        <Form.Item
          name="price"
          label="商品价格"
          rules={[
            {
              required: true,
              message: "请输入商品价格,只支持小数",
              // type: "float",
              // // 这里如果是type为number、integer、float需要把值转换一下格式，否则验证不通过，这是官方一个BUG，不可用但又列在文档里面了
              // transform: (value) => {
              //   return Number(value) ? Number(value) : 0;
              // },
            },
            {
              validator: priceValidate, // 自定义校验，和上面的规则同时生效
            },
          ]}
        >
          <Input placeholder="请输入商品价格" />
        </Form.Item>
        <Form.Item label="主图">
          <Upload
            name="file"
            listType="picture-card"
            className="avatar-uploader"
            showUploadList={false}
            action={SERVER_URL + "/api/v1/common/file_upload"}
            onChange={handleChange}
          >
            {imageUrl ? (
              <img
                src={SERVER_URL + imageUrl}
                alt="avatar"
                style={{
                  width: "100%",
                }}
              />
            ) : (
              uploadButton
            )}
          </Upload>
        </Form.Item>
        <Form.Item>
          <Button htmlType="submit" type="primary">
            保存
          </Button>
        </Form.Item>
      </Form>
    </Card>
  );
}

export default Edit;
