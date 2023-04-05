interface Resp<T> {
  code: string;
  msg: string;
  data?: T;
  field?: string;
  explain?: string;
  requestId?: string;
  implVersion?: string;
  buildTime?: string;
  buildTimeParse?: string;
}

export default Resp
