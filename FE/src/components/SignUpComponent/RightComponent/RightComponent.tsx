import React from 'react';
import styled from 'styled-components';
import { RightComponentBackGround, TextDiv, TextInput, Button } from '../../common';

const EmailForm = styled.div`
  display: flex;

  button {
    margin: 0px;
    margin-top: 87px;
    margin-right: 30%;
  }
`;

interface RightComponentProps {
  email: string;
  password: string;
  repassword: string;
  name: string;
  onChangeEmail: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onChangePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onChangeRePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onChangeName: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  onCheck: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}

export const RightComponent = ({
  email,
  onChangeEmail,
  password,
  onChangePassword,
  onClick,
  repassword,
  onChangeRePassword,
  name,
  onChangeName,
  onCheck,
}: RightComponentProps): JSX.Element => {
  return (
    <RightComponentBackGround>
      <TextDiv size='80px' margin='0px' text='SIGNUP' />
      <EmailForm>
        <TextInput placeholder='Email' inputType='text' value={email} onChange={onChangeEmail} />
        <Button eventHandler={onCheck} text='Check' />
      </EmailForm>
      <TextInput placeholder='Password' inputType='password' value={password} onChange={onChangePassword} />
      <TextInput
        placeholder='Re-Password'
        inputType='password'
        value={repassword}
        onChange={onChangeRePassword}
      />
      <TextInput placeholder='Name' inputType='text' value={name} onChange={onChangeName} />
      <Button eventHandler={onClick} text='SIGNUP' />
    </RightComponentBackGround>
  );
};
