package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;

import drawables.Cartesian;
import drawables.MatrixAnimator;
import drawables.MatrixDots;
import drawables.VectorAnimated;
import panel.AnimatedJPanel;
import panel.CartesianJPanelConvert;

public class Main {

	private static Timer t;
	private static Random rd;
	private JFrame frmAnusioMatrixOperator;
	private AnimatedJPanel pnView;
	private CartesianJPanelConvert cartesianconvert;
	private JPanel pnMatriz;
	private JPanel panel_2;
	private JButton btnRecalc;
	private JButton btnRand;
	private JPanel panel_3;
	private JTextField tfM00;
	private JTextField tfM01;
	private JTextField tfM10;
	private JTextField tfM11;
	private JLabel lblMatriz;
	private MatrixDots mIden;
	private MatrixDots mNew;
	private MatrixAnimator mAnim;
	private JButton btnAnimate;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField tfVx1;
	private JTextField tfVy2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField tfVy1;
	private JTextField tfVx2;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JLabel lblVector;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		rd = new Random();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
							if ("Nimbus".equals(info.getName())) {
								UIManager.setLookAndFeel(info.getClassName());
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					Main window = new Main();
					window.frmAnusioMatrixOperator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		cartesianconvert = new CartesianJPanelConvert(pnView);
		initialize();

		mIden = new MatrixDots(cartesianconvert);
		mNew = new MatrixDots(cartesianconvert);

		mAnim = new MatrixAnimator(mIden, mNew);

		pnView.setDrawable(mIden);

		pnView.setDrawable(mAnim);
		pnView.setDrawable(new Cartesian());
//		pnView.setDrawable(new Vector(1,2));
		pnView.setLayout(new BorderLayout(0, 0));
		t = new Timer(30, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pnView.repaint();
				pnView.updateLogic();

			}
		});
		t.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAnusioMatrixOperator = new JFrame();
		frmAnusioMatrixOperator.setTitle("Anusio's matrix operator view v0.0.1");
		frmAnusioMatrixOperator.setBounds(100, 100, 905, 659);
		frmAnusioMatrixOperator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnComander = new JPanel();
		pnComander.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnComander.setSize(new Dimension(200, 200));
		frmAnusioMatrixOperator.getContentPane().add(pnComander, BorderLayout.SOUTH);
		pnComander.setLayout(new BorderLayout(0, 0));

		pnMatriz = new JPanel();
		pnComander.add(pnMatriz, BorderLayout.CENTER);
		pnMatriz.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		pnMatriz.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		tfM00 = new JTextField();
		tfM00.setText("1");
		panel_3.add(tfM00);
		tfM00.setColumns(10);

		tfM01 = new JTextField();
		tfM01.setText("0");
		panel_3.add(tfM01);
		tfM01.setColumns(10);

		tfM10 = new JTextField();
		tfM10.setText("0");
		panel_3.add(tfM10);
		tfM10.setColumns(10);

		tfM11 = new JTextField();
		tfM11.setText("1");
		panel_3.add(tfM11);
		tfM11.setColumns(10);

		lblMatriz = new JLabel("Matriz");
		pnMatriz.add(lblMatriz, BorderLayout.NORTH);
		
		panel_4 = new JPanel();
		pnMatriz.add(panel_4, BorderLayout.WEST);

		panel_2 = new JPanel();
		pnComander.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		btnRecalc = new JButton("Recalculate");
		btnRecalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actRecalculate();
			}
		});
		panel_2.add(btnRecalc);

		btnRand = new JButton("RandomizeMat");
		btnRand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfM00.setText("" + (rd.nextDouble() * 3));
				tfM01.setText("" + (rd.nextDouble() * 3));
				tfM10.setText("" + (rd.nextDouble() * 3));
				tfM11.setText("" + (rd.nextDouble() * 3));
			}
		});

		btnNewButton_3 = new JButton("Normalize");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double v1x = Double.parseDouble(tfM00.getText());
					double v1y = Double.parseDouble(tfM10.getText());
					double v2x = Double.parseDouble(tfM01.getText());
					double v2y = Double.parseDouble(tfM11.getText());

					double c1 = Math.sqrt(v1x*v1x + v1y*v1y);
					double c2 = Math.sqrt(v2x*v2x + v2y*v2y);
					tfM00.setText(""+(v1x/c1));
					tfM10.setText(""+(v1y/c1));
					tfM01.setText(""+(v2x/c2));
					tfM11.setText(""+(v2y/c2));
					lblMatriz.setText("Matrix Normalized");
				} catch (Exception e2) {
					lblMatriz.setText("Coudnot normalize");
				}
			}
		});
		panel_2.add(btnNewButton_3);
		panel_2.add(btnRand);

		btnAnimate = new JButton("Animate");
		btnAnimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				mAnim.setAnimatorfac(0);
				mAnim.changeReverse();
			}
		});
		panel_2.add(btnAnimate);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnComander.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		tfVx1 = new JTextField();
		tfVx1.setText("0");
		panel_1.add(tfVx1);
		tfVx1.setColumns(10);

		tfVy1 = new JTextField();
		tfVy1.setText("0");
		panel_1.add(tfVy1);
		tfVy1.setColumns(10);

		tfVx2 = new JTextField();
		tfVx2.setText("1");
		panel_1.add(tfVx2);
		tfVx2.setColumns(10);

		tfVy2 = new JTextField();
		tfVy2.setText("1");
		tfVy2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(tfVy2);
		tfVy2.setColumns(10);

		btnNewButton = new JButton("AddVector");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float x1 = Float.parseFloat(tfVx1.getText());
					float y1 = Float.parseFloat(tfVy1.getText());
					float x2 = Float.parseFloat(tfVx2.getText());
					float y2 = Float.parseFloat(tfVy2.getText());
					mAnim.setVector(new VectorAnimated(x1, y1, x2, y2));
					lblVector.setText("Vector added");
				} catch (Exception ex) {
					lblVector.setText("Vector Not Valid");
				}
			}
		});
		panel_1.add(btnNewButton);

		btnNewButton_1 = new JButton("CalculateVector");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double tmp[][] = new double[2][2];
				try {
					float x1 = Float.parseFloat(tfVx1.getText());
					float y1 = Float.parseFloat(tfVy1.getText());
					float x2 = Float.parseFloat(tfVx2.getText());
					float y2 = Float.parseFloat(tfVy2.getText());
					VectorAnimated tmp2 = new VectorAnimated(x1, y1, x2, y2);
					
					tmp[0][0] = Double.parseDouble(tfM00.getText());
					tmp[1][1] = Double.parseDouble(tfM11.getText());
					tmp[0][1] = Double.parseDouble(tfM01.getText());
					tmp[1][0] = Double.parseDouble(tfM10.getText());
					
					MatrixDots tmp3 = new MatrixDots(cartesianconvert);
					tmp3.setMatBase(tmp);
					
					tmp2.recalculate(cartesianconvert, tmp3.getAlgMatBase());
					lblVector.setText(String.format("X1:%.2f | Y1:%.2f | X2:%.2f | Y2:%.2f | ", tmp2.getX3(), tmp2.getY3(), tmp2.getX4(), tmp2.getY4()));
				} catch (Exception ex) {
					lblVector.setText("Vector Not Valid");
				}
			}
		});
		panel_1.add(btnNewButton_1);

		btnNewButton_2 = new JButton("remove vector");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float x1 = Float.parseFloat(tfVx1.getText());
					float y1 = Float.parseFloat(tfVy1.getText());
					float x2 = Float.parseFloat(tfVx2.getText());
					float y2 = Float.parseFloat(tfVy2.getText());
					mAnim.removeVector(new VectorAnimated(x1, y1, x2, y2));
					lblVector.setText("Vector removed");
				} catch (Exception ex) {
					lblVector.setText("Vector Not removed!!!");
				}
			}
		});
		panel_1.add(btnNewButton_2);

		btnNewButton_4 = new JButton("Clear Vectors");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mAnim.clearVectors();
			}
		});
		panel_1.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Show origin");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mAnim.setShowOrigi();
			}
		});
		panel_1.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Show landed");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mAnim.setShowLanded();
			}
		});
		panel_1.add(btnNewButton_6);
		
		lblVector = new JLabel("Vector");
		panel.add(lblVector, BorderLayout.NORTH);
		
		panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.EAST);

		pnView = new AnimatedJPanel(cartesianconvert);
		frmAnusioMatrixOperator.getContentPane().add(pnView, BorderLayout.CENTER);
	}

	protected void actRecalculate() {
		double tmp[][] = new double[2][2];
		mAnim.setAnimatorfac(0);
		try {
			tmp[0][0] = Double.parseDouble(tfM00.getText());
			tmp[1][1] = Double.parseDouble(tfM11.getText());
			tmp[0][1] = Double.parseDouble(tfM01.getText());
			tmp[1][0] = Double.parseDouble(tfM10.getText());
			mNew.setMatBase(tmp);
			lblMatriz.setText("Matrix");
			mNew.recalculate(cartesianconvert);
			mNew.randColor();
		} catch (Exception e) {
			lblMatriz.setText("Matrix Not Valid");
		}
	}

}
