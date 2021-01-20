import * as React from 'react';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import LoginComponent from '../components/LoginComponent/LoginComponent';
import { GlobalStyle } from '../styles/GlobalStyle';
import { store, persistor } from '../store/store';

const Index: React.FunctionComponent = () => {
  return (
    <div>
      <Provider store={store}>
        <PersistGate persistor={persistor}>
          <GlobalStyle />
          <LoginComponent />
        </PersistGate>
      </Provider>
    </div>
  );
};

export default Index;
