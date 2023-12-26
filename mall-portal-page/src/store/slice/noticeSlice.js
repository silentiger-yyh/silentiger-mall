import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    count: 50,
    isAllRead: false
};
export const noticeSlice = createSlice({
    name: "noticeSlice", // 命名空间，在调用action的时候会默认的设置为action的前缀,保证唯一.不重名
    initialState: initialState,
    reducers: {
        // reducer函数 state当前组件的数据
        //第二个参数为{payload:{},type:"""}
        increment(state) {
            state.count ++;
            state.isAllRead = false
        },
        // 标记已读
        decrement(state) {
            if (state.count > 0) {
                state.count --;
                if (state.count === 0) {
                    state.isAllRead = true
                }
            }else {
                state.isAllRead = true
            }
        },
        readAll(state) {
            state.count = 0;
            state.isAllRead = true;
        },
        setNotice(state, {payload}) {
            state.count = payload.count
            if (payload.count > 0) {
                state.isAllRead = false
            }
        }
    },
});
export const { increment, decrement,readAll,setNotice} = noticeSlice.actions;
export const selectCount = (state) => state.noticeSlice.count;
export const selectIsAllRead = (state) => state.noticeSlice.isAllRead;
export default noticeSlice.reducer;
