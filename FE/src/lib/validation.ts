const IDREG = /^[a-zA-Z0-9]([a-zA-Z0-9])*@[a-zA-Z0-9]([a-zA-Z0-9])*.[a-zA-Z]{2,3}$/i;
const PWREG = /^[a-zA-Z0-9]{8,16}$/i;
const NAMEREG = /^[a-zA-Z0-9]{4,20}$/i;

interface validationProps {
  id: string;
  password: string;
  repassword: string;
  name: string;
}

export const validation = ({ id, password, repassword, name }: validationProps): any => {
  console.log('validation');
  if (!IDREG.test(id)) return 1;
  if (password !== repassword) return 2;
  if (!PWREG.test(password)) return 3;
  if (!NAMEREG.test(name)) return 4;
  return 0;
};
