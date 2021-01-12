import React, { ChangeEvent, ReactEventHandler, useState } from 'react';
import styled from 'styled-components';
import { TextDiv } from '../common/TextDiv';
import { TextInput } from '../common/TextInput';
// import { Button } from '../common/Button';

const Wrapper = styled.div`
  width: 100%;
  min-height: 100vh;
  color: white;
  z-index: 1;
  position: relative;

  &::after {
    width: 100%;
    min-height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    background-image: url('/static/background.jpg');
    color: white;
    z-index: -1;
    position: absolute;
    left: 0;
    top: 0;
    content: '';
    opacity: 0.9;
  }
`;

const LeftComponent = styled.div`
  width: 60%;
  background-color: none;
  font-size: 60px;
  padding: 10% 7%;
  padding-bottom: 0px;
  color: white;
`;

const RightComponent = styled.div`
  width: 40%;
  font-size: 80px;
  padding: 10% 5%;
  padding-bottom: 0px;
  z-index: 3;
  position: relative;
  background-color: black;
  opacity: 0.7;
`;

const ComponentWrapper = styled.div`
  display: flex;
  height: 100vh;
`;

const LoginComponent = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const onChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const onClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    setEmail('');
    setPassword('');
  };

  return (
    <>
      <Wrapper>
        <ComponentWrapper>
          <LeftComponent>
            <TextDiv size='70px' margin='0px' text='Welcome !' />
            <TextDiv size='130px' margin='20px' text='Every Portfolio' />
            <TextDiv size='60px' margin='370px' text='Don’t have your PortFolio?' />
            <TextDiv size='30px' margin='0px' text='You can make your own portfolio easily' />
          </LeftComponent>
          <RightComponent>
            <TextDiv size='80px' margin='0px' text='LOGIN' />
            <TextInput placeholder='email' inputType='text' value={email} onChange={onChangeEmail} />
            <TextInput
              placeholder='password'
              inputType='password'
              value={password}
              onChange={onChangePassword}
            />
            <button type='button' onClick={(e) => onClick(e)}>
              제출
            </button>
          </RightComponent>
        </ComponentWrapper>
      </Wrapper>
    </>
  );
};

export default LoginComponent;
