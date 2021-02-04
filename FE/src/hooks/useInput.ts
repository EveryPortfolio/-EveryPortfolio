import { useState } from 'react';

export const useInput = (initialValue: any): any => {
  const [value, setValue] = useState(initialValue);

  const onChangeValue = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  };

  return [value, setValue, onChangeValue];
};
