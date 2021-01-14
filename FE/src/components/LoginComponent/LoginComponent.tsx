import React, { useState } from 'react';
import styled from 'styled-components';
import Link from 'next/link';
import {
  TextDiv,
  TextInput,
  Button,
  AuthBackground,
  LeftComponentBackGround,
  RightComponentBackGround,
} from '../common';

const LinkWrapper = styled.div`
  margin-top: 100px;
  font-size: 24px;
`;

const FlexWrapper = styled.div`
  display: flex;

  div {
    margin-left: 10px;
    margin-top: 0px !important;
  }
`;

const LinkDiv = styled.div`
  margin-top: 20px;
  color: #2a8de8;
  cursor: pointer;
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
      <AuthBackground>
        <LeftComponentBackGround>
          <TextDiv size='70px' margin='0px' text='Welcome !' />
          <TextDiv size='130px' margin='20px' text='Every Portfolio' />
          <TextDiv size='60px' margin='370px' text='Don’t have your PortFolio?' />
          <TextDiv size='30px' margin='0px' text='You can make your own portfolio easily' />
        </LeftComponentBackGround>
        <RightComponentBackGround>
          <TextDiv size='80px' margin='0px' text='LOGIN' />
          <TextInput placeholder='Email' inputType='text' value={email} onChange={onChangeEmail} />
          <TextInput
            placeholder='Password'
            inputType='password'
            value={password}
            onChange={onChangePassword}
          />
          <Button eventHandler={onClick} text='LOGIN' />
          <LinkWrapper>
            <FlexWrapper>
              <span>New to Every Portfolio? </span>
              <Link href='/signup'>
                <LinkDiv>Create an Account!</LinkDiv>
              </Link>
            </FlexWrapper>
            <Link href='/find-id'>
              <LinkDiv>Forgot your ID ?</LinkDiv>
            </Link>
            <Link href='/find-id'>
              <LinkDiv>Forgot your Password ?</LinkDiv>
            </Link>
          </LinkWrapper>
        </RightComponentBackGround>
      </AuthBackground>
    </>
  );
};

export default LoginComponent;
