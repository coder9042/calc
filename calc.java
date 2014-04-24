import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class calc
{
	JFrame frame;
	JButton b[]=new JButton[10];
	JButton add,sub,div,prod,inverse,power,sign,clr,equals,back,decimal,factorial;
	JTextArea area;
	JPanel panel;
	boolean tp1=false,tp2=false,dp=false;
	int ndp=0;
	double val1,val2,ans;
	char symbol='\0';
	public void go()
	{
		frame=new JFrame("Calculator");
		frame.setSize(255,245);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		area=new JTextArea(2,15);
		Color grey=new Color(128,128,128);
		Font font=new Font("comic sans ms",Font.BOLD,15);
		panel.setBackground(grey);
		area.setFont(font);
		panel.add(area);
		clr=new JButton(" CLR ");
		back=new JButton("BACK");
		inverse=new JButton("  1/x  ");
		panel.add(clr);
		for(int i=1;i<=9;i++)
		{
			b[i]=new JButton(Integer.toString(i));
			if(i==4)
			{
				panel.add(back);
			}
			if(i==7)
			{
				panel.add(inverse);
			}
			panel.add(b[i]);
		}
		add=new JButton("+");
		sub=new JButton("-");
		prod=new JButton("*  ");
		div=new JButton("/  ");
		decimal=new JButton(".");
		equals=new JButton("=");
		power=new JButton("^");
		sign=new JButton("+-");
		factorial=new JButton("!");
		panel.add(add);
		panel.add(sub);
		panel.add(decimal);
		b[0]=new JButton("0");
		panel.add(b[0]);
		panel.add(equals);
		panel.add(prod);
		panel.add(div);
		panel.add(power);
		panel.add(sign);
		panel.add(factorial);
		frame.setContentPane(panel);
		frame.setVisible(true);
		setListener();
	}
	public void setListener()
	{
		add.addActionListener(new calculate());
		sub.addActionListener(new calculate());
		prod.addActionListener(new calculate());
		div.addActionListener(new calculate());
		inverse.addActionListener(new calculate());
		power.addActionListener(new calculate());
		sign.addActionListener(new calculate());
		factorial.addActionListener(new calculate());
		clr.addActionListener(new function());
		equals.addActionListener(new function());
		back.addActionListener(new function());
		for(int c=0;c<10;c++)
		{
			b[c].addActionListener(new values());
		}
		decimal.addActionListener(new values());
	}
	public static void main(String args[])
	{
		calc obj=new calc();
		obj.go();
	}
	class calculate implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==add || e.getSource()==sub || e.getSource()==prod || e.getSource()==div || e.getSource()==power)
			{
				if(tp1==false)
				{
					symbol=getSymbol(e);
					//System.out.println(symbol);
					area.append("\n"+symbol);
					tp1=true;
					dp=false;
					ndp=0;
				}
			}
			else if(e.getSource()==inverse)
			{
				if(symbol=='\0')
				{
					val1=1/val1;
					area.setText(val1+"");
				}
			}
			else if(e.getSource()==factorial)
			{
				if(symbol=='\0')
				{
					val1=fact((long)val1);
					area.setText(val1+"");
				}
				if(symbol!='\0' && (long)val2!=0)
				{
					val2=fact((long)val2);
					area.setText(val1+"\n"+symbol+val2);
				}
			}
			else
			{
				if(symbol=='\0')
				{
					val1=-val1;
					area.setText(val1+"");
				}
			}
		}
		public char getSymbol(ActionEvent e)
		{
			JButton temp=(JButton)e.getSource();
			String symb=temp.getText();
			return symb.charAt(0);
		}
		public double fact(long x)
		{
			double f=1;
			for(long j=x;j>1;j--)
				f=f*j;
			return f;
		}
	}
	class function implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==clr)
			{
				area.setText("");
				val1=0.0;
				val2=0.0;
				ans=0.0;
				symbol='\0';
				dp=false;
				ndp=0;
				tp1=false;
				tp2=false;
			}
			else if(e.getSource()==equals)
			{
				switch(symbol)
				{
					case '+':ans=val1+val2;break;
					case '-':ans=val1-val2;break;
					case '*':ans=val1*val2;break;
					case '/':ans=val1/val2;break;
					case '^':ans=Math.pow(val1,val2);break;
				}
				area.setText(ans+"");
				val1=ans;
				val2=0.0;
				symbol='\0';
				dp=true;
				tp1=false;
				tp2=false;
				ndp=0;
			}
			else
			{
				if(tp1==false)
				{
					if((long)val1==val1)
					{
						String x=(long)val1+"";
						int l=x.length();
						String y=x.substring(0,l-1);
						long temp=Long.parseLong(y);
						area.setText(temp+"");
						val1=temp;
						dp=false;
						ndp=0;
					}
					else
					{
						String x=Double.toString(val1);
						int l=x.length();
						String y=x.substring(0,l-1);
						val1=Double.parseDouble(y);
						area.setText(val1+"");
						ndp=ndp-1;
					}
				}
				else
				{
					if((long)val2==0)
					{
						symbol='\0';
						tp1=false;
						if((long)val1==val1)
							area.setText((long)val1+"");
						else
							area.setText(val1+"");
					}
					else
					{
						if((long)val2==val2)
						{
							String x=Double.toString(val2);
							int l=x.length();
							String y=x.substring(0,l-1);
							long temp=Long.parseLong(y);
							val2=temp;
							if((long)val1==val1)
								area.setText((long)val1+"\n"+symbol+temp+"");
							else
								area.setText(val1+"\n"+symbol+temp+"");
							dp=false;
							ndp=0;
						}
						else
						{
							String x=val2+"";
							int l=x.length();
							String y=x.substring(0,l-1);
							val2=Double.parseDouble(y);
							if((long)val1==val1)
								area.setText((long)val1+"\n"+symbol+val2+"");
							else
								area.setText(val1+"\n"+symbol+val2+"");
							ndp=ndp-1;
						}
					}
				}
			}
		}
	}
	class values implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()!=decimal)
			{
				if(symbol=='\0')
				{
					Long num=Long.parseLong(getStr(e));
					if(dp==false)
					{
						long x=(long)val1;
						String y=Long.toString(x);
						y+=num;
						x=Long.parseLong(y);
						val1=x;
						area.setText(x+"");
					}
					else
					{
						double dec=Math.pow(10,ndp);
						double temp=num/dec;
						val1+=temp;
						String temp2=Double.toString(val1);
						int pos=temp2.indexOf('.');
						String print=""+(long)val1+"."+temp2.substring(pos+1,pos+1+ndp);
						area.setText(print);
						ndp++;
					}
				}
				else
				{
					Long num=Long.parseLong(getStr(e));
					if(dp==false)
					{
						long x=(long)val2;
						String y=Long.toString(x);
						y+=num;
						x=Long.parseLong(y);
						val2=x;
						area.append(num+"");
					}
					else
					{
						double dec=Math.pow(10,ndp);
						double temp=num/dec;
						val2+=temp;
						area.append(num+"");
						ndp++;
					}
				}
			}
			else
			{
				if(dp==false)
				{
					dp=true;
					area.append(".");
					ndp++;
				}
			}
		}
		public String getStr(ActionEvent e)
		{
			for(int i=0;i<10;i++)
			{
				if(e.getSource()==b[i])
				{
					return Integer.toString(i);
				}
			}
			return null;
		}
	}
}
