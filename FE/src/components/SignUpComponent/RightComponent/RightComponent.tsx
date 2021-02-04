import React from 'react';
import styled from 'styled-components';
import { RightComponentBackGround, TextDiv, TextInput, Button } from '../../common';

const IsCheck = styled.div<{ email: string; duplication: boolean }>`
  height: 15px;
  visibility: ${(props) => {
    return props.email !== '' && !props.duplication ? 'visible' : 'hidden';
  }};
`;
interface RightComponentProps {
  email: string;
  password: string;
  repassword: string;
  name: string;
  duplication: boolean;
  onChangeEmail: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onChangePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onChangeRePassword: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onChangeName: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
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
  duplication,
}: RightComponentProps): JSX.Element => {
  return (
    <RightComponentBackGround>
      <TextDiv size='80px' margin='0px' text='SIGNUP' />
      <TextInput placeholder='Email' inputType='text' value={email} onChange={onChangeEmail} />
      <IsCheck email={email} duplication={duplication}>
        <TextDiv size='15px' margin='5px' text='아이디의 형식이 맞지 않거나 중복되는 아이디가 존재합니다.' />
      </IsCheck>
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
