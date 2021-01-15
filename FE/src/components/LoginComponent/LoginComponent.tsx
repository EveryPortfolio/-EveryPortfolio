import React from 'react';
import { AuthBackground } from '../common';
import { LeftComponent } from './LeftComponent/LeftComponent';
import { RightComponent } from './RightComponent/RightComponent';

const LoginComponent = (): JSX.Element => {
  console.log('Component render');
  return (
    <>
      <AuthBackground>
        <LeftComponent />
        <RightComponent />
      </AuthBackground>
    </>
  );
};

export default LoginComponent;
