// import { legacy_createStore as createStore } from "redux";
// const notice = (state = { isAllRead: false, count: 8 }, action) => {
//   switch (action.type) {
//     case "READ_ALL":
//       return { ...state, isAllRead: true };
//     default:
//       return state;
//   }
// };
//
// const store = createStore(notice);
// export default store;

import { configureStore } from "@reduxjs/toolkit";
import noticeSlice from "./slice/noticeSlice";
export default  configureStore({
  reducer: {
    noticeSlice
  }
})

