import { createStore } from 'vuex'

const USER = "user";

export default createStore({
  state: {
    user: window.SessionStorage.get(USER) || {},
  },
  mutations: {
    setUser (state, _user) {
      state.user = _user;
      window.SessionStorage.set(USER, _user);
    }
  },
})
