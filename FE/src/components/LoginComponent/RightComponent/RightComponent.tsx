import React, { useState } from 'react';
import { useRouter } from 'next/router';
import { useDispatch } from 'react-redux';
import { RightComponentBackGround, TextDiv, TextInput, Button } from '../../common';
import { Links } from './Links/Links';
import { requestLogin } from '../../../store/modules/user';

export const RightComponent = (): JSX.Element => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();
  const dispatch = useDispatch();

  console.log('render Right');

  const onChangeEmail = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change Email');
    setEmail(e.target.value);
  };

  const onChangePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    console.log('change password');
    setPassword(e.target.value);
  };

  const onClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    console.log('click button');
    const params = { id: email, password };
    dispatch(requestLogin(params)).then((res) => {
      console.log(res);
      setEmail('');
      setPassword('');
      // router.push('/');
    });
  };

  return (
    <RightComponentBackGround>
      <TextDiv size='80px' margin='0px' text='LOGIN' />
      <TextInput placeholder='Email' inputType='text' value={email} onChange={onChangeEmail} />
      <TextInput placeholder='Password' inputType='password' value={password} onChange={onChangePassword} />
      <Button eventHandler={onClick} text='LOGIN' />
      <Links />
    </RightComponentBackGround>
  );
};
