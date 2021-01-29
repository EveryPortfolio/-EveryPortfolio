import React from 'react';
import { ReactReduxContext } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { GlobalStyle } from '../styles/GlobalStyle';
import { wrapper } from '../store/store';

export default wrapper.withRedux(function MyApp({ Component, pageProps }) {
  return (
    <ReactReduxContext.Consumer>
      {({ store }) => {
        return (
          <PersistGate persistor={store.__persistor}>
            <GlobalStyle />
            <Component {...pageProps} />
          </PersistGate>
        );
      }}
    </ReactReduxContext.Consumer>
  );
});
