export const createPromiseThunk = (type: string, promiseCreator: (params: any) => Promise<string>) => (
  params: any,
  pushStateHandler: any,
) => {
  return (dispatch) => {
    const response = dispatch({
      type,
      payload: promiseCreator(params),
    });

    response.then((res) => {
      console.log('result:', res);
      pushStateHandler();
    });
  };
};

export const reducerUtils = {
  initial: (initialData = null): any => ({
    loading: false,
    loginSuccess: initialData,
    error: null,
  }),
  loading: (prevState = null): any => ({
    loading: true,
    loginSuccess: prevState,
    error: null,
  }),
  success: (payload: boolean): any => ({
    loading: false,
    loginSuccess: payload,
    error: null,
  }),
  error: (error: Error): any => ({
    loading: false,
    loginSuccess: null,
    error,
  }),
};

export const handleAsyncActions = (type: string, key: string): any => {
  const [PENDING, FULFILLED, REJECTED] = [`${type}_PENDING`, `${type}_FULFILLED`, `${type}_REJECTED`];
  return (state: any, action: any) => {
    switch (action.type) {
      case PENDING:
        return {
          ...state,
          [key]: reducerUtils.loading(),
        };
      case FULFILLED:
        return {
          ...state,
          [key]: reducerUtils.success(action.payload),
        };
      case REJECTED:
        return {
          ...state,
          [key]: reducerUtils.error(action.payload),
        };
      default:
        return state;
    }
  };
};
